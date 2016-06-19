(ns com.agilecreativity.clj_audiotagger.cli
  (:require [clojure.string :as string])
  (:gen-class))

(def cli-options
  [["-b" "--base-dir DIR"
    "The staring directory" :default "."]
   ["-f" "--file-name-as-title"
    "Use file name as title"]
   ["-t" "--position-as-track-order"
    "Use position as track order"]
   ["-u" "--set-shared-tags"
    "Update shared tags"]
   ["-c" "--cover COVER_FILE"
    "The shared cover file that will be updated to"]
   ["-h" "--help"]])

(defn usage [options-summary]
  (->> ["Update mp3 id3 tag using simple rules."
        ""
        "Usage: clj_audiotagger [options]"
        options-summary
        ""
        "Options:"
        ""
        "--base-dir DIR starting directory"
        "--cover COVER_FILE the shared cover file if any"
        "--filename-as-title"
        "--position-as-track-order"
        "--set-shared-tags tags"
        "  title   TITLE"
        "  album   ALBUM"
        "  artist  ARTIST"
        "  genre   GENRE"
        "  year    YEAR"
        "  comment COMMENT"
        ""
        "Please refer to the manual page for more information."]
       (string/join \newline)))

(defn error-msg [errors]
  (str "The following error occured while parsing your commands:\n\n"
       (string/join \newline errors)))

(defn exit [status msg]
  (println msg)
  (System/exit status))
