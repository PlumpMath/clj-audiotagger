(defproject clj-audiotagger "0.1.0-SNAPSHOT"
  :description "Useful mp3 tagger library wrap on top of jaudiotagger and claudio library"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:uberjar {:aot :all}}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org/jaudiotagger "2.0.3"]
                 [claudio "0.1.3"]
                 [org.clojure/tools.cli "0.3.5"]
                 [me.raynes/fs "1.4.6"]]
  :main com.agilecreativity.clj_audiotagger.main)
