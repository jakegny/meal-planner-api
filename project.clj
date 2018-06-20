(defproject meal-planner-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [com.datomic/client-pro "0.8.16"]
                 [ring/ring-devel "1.6.3"]
                 [ring/ring-core "1.6.3"]
                 [compojure "1.6.1"]
                 [http-kit "2.3.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [ring/ring-json "0.4.0"]]
  :main meal-planner-api.core)
  ; :plugins [[lein-ring "0.12.4"]]
  ; :ring {:handler meal-planner-api.core/app
  ;        :nrepl {:start? true
  ;                :port 9998}}
  ; :profiles {:dev
  ;            {:dependencies [[javax.servlet/servlet-api "2.5"]
  ;                            [ring-mock "0.1.5"]]}})
