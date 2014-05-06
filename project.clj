(defproject magicscraper "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 ;;JDBC deps
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 ;;Enlive dep
                 [enlive "1.1.5"]
                 ;;Make sure this shit works with Lighttable
                 [org.clojure/clojurescript "0.0-2030"]
                 [lein-light-nrepl "0.0.13"]]
  :repl-options {:nrepl-middleware [lighttable.nrepl.handler/lighttable-ops]}
  :aot :all
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler magicscraper.handler/app
         :init magicscraper.handler/init
         :destroy magicscraper.handler/destroy}
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.1"]]}})
