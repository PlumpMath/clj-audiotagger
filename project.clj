(defproject clj-audiotagger "0.1.1-SNAPSHOT"
  :description "Useful CLI audio tagger utility/library wrap on top of jaudiotagger and claudio library"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:uberjar {:aot :all}}
  :source-paths ["src/clj"]
  :java-source-paths ["src/java"]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [net.jthink/jaudiotagger "2.2.3"]
                 [claudio "0.1.3"]
                 [org.clojure/tools.cli "0.3.5"]
                 [me.raynes/fs "1.4.6"]]
  :repositories {"jaudiotagger-repository" "https://dl.bintray.com/ijabz/maven"}
  :main com.agilecreativity.clj_audiotagger.main)
