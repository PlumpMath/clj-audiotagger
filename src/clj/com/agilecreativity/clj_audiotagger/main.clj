(ns com.agilecreativity.clj_audiotagger.main
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [clojure.string :as string]
            [clojure.tools.cli :refer [parse-opts]]
            [com.agilecreativity.clj_audiotagger.cli :refer :all :as cli]
            [com.agilecreativity.clj_audiotagger.util :as util]
            [me.raynes.fs :as fs]
            [me.raynes.fs.compression :refer :all])
  (:gen-class))

(defn mp3-files
  "List all mp3 files from a given directory ignore case for extension"
  [base-dir]
  (filter #(contains? #{".mp3" ".MP3"} (fs/extension %)) (fs/list-dir base-dir)))

(defn -main [& args]
  ;; Turn off the logging for now
  (.setLevel (java.util.logging.Logger/getLogger "org.jaudiotagger")
             java.util.logging.Level/OFF)

  (let [{:keys [options arguments errors summary]}
        (parse-opts args cli-options :in-order true)]
    (if-let [files (mp3-files (:base-dir options))]
      (if (empty? files)
        (println "No mp3 file found in your given directory :" (:base-dir options))))

    ;; Handle help and error conditions
    (cond
      ;; User asking for help
      (:help options)
      (exit 0 (usage summary))

      ;; Directory having no mp3 files
      (:base-dir options)
      (if (empty? (mp3-files (:base-dir options)))
        (exit 1 (usage summary)))

      ;; Other errors if any
      errors (exit 1 (error-msg errors)))

    ;; If we get here, we have the files and some valid options
    (let [files (mp3-files (:base-dir options))]

      ;; Let's check our options and run appropriate task
      (println "Your options: " options)
      (if (:file-name-as-title options)
        (doseq [file files]
          ;; Note: this is necessary if you have the id3v1 only!
          ;; If not jaudiotagger will scream at you!
          (util/create-id3v2-if-none-exist! file)
          (util/set-title-to-file-name! file)))

      (if (:position-as-track-order options)
        (do
          (println "Set the track to posion of the input files:")
          (util/set-track-to-position! files)))

      (if (:set-shared-tags options)
        (when-not (empty? arguments)
          (when-not (even? (count arguments))
            (throw (Exception. "Tag-value pairs must be even number")))
          (doseq [[k v] (partition 2 arguments)]
            (util/update-common-tags! files {(keyword k) v}))))
      (util/show-tags files))))
