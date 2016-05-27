(ns com.agilecreativity.clj_audiotagger.util
  (:require [claudio.id3     :as id3]
            [clojure.java.io :as io]
            [clojure.string  :as string]
            [me.raynes.fs    :as fs]
            [me.raynes.fs.compression :refer :all])
  (:import org.jaudiotagger.audio.AudioFileIO
           org.jaudiotagger.tag.FieldKey))

(defn update-tag!
  "Update multiple id3 tags for a single mp3 file

Tags are map of key and value where the key can be any valid id3 tags like
:title, :artist, :album, :year, :comment, :genre, :track, :track-total, :comment"
  [mp3-file tags]
  (doseq [[tag-key tag-value] tags]
    (id3/write-tag! mp3-file tag-key tag-value)))

;; New method to workaround the `id3v1` tag
(defn create-id3v2-if-none-exist!
  "Delete id3v1 if one exist, create dummy id3v2 if none exist.

Only deal with id3v2 as the `write-tag!` above is not working properly when
the input file contain only  `id3v1` tag"
  [f]
  (let [audio-file (org.jaudiotagger.audio.AudioFileIO/read f)]
    (if (.hasID3v1Tag audio-file)
      (do
        ;; Remove id3v1 if we have one
        (.delete audio-file (.getID3v1Tag audio-file))
        (.commit audio-file)))
    (if-not (.hasID3v2Tag audio-file)
      (do
        ;; Add id3v2 if we don't have one
        (.setTag audio-file (org.jaudiotagger.tag.id3.ID3v24Tag.))
        (.commit audio-file)))))

(defn set-title-to-file-name!
  "Set the title to the current file name.

Good when you have the file with bad name and want to set the title to
the file name."
  [mp3-file]
  (let [full-name (str mp3-file)
        base-name (fs/base-name full-name true)]
    (update-tag! mp3-file {:title base-name})))

(defn update-common-tags!
  "Update multiple mp3 files using the same tags.

Work best if the file contain common tag value like :artist, :album, :year, :genre"
  [mp3-files tags]
  (doseq [file mp3-files]
    (update-tag! file tags)))

(defn set-track-to-position!
  "Update the :track and :track-total using the current position of the input.

The two tags are :track and :track-total"
  [mp3-files]
  (doseq [[idx item] (map-indexed vector mp3-files)]
    (update-tag! item {:track (str (inc idx))
                       :track-total (str (count mp3-files))})))

(defn show-tags
  "Show the tags information for list of files"
  [mp3-files]
  (doseq [mp3-file mp3-files]
    (println (id3/read-tag mp3-file))))
