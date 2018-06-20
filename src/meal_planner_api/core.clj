(ns meal-planner-api.core
  (:use [org.httpkit.server :only [run-server]])
  (:require
            [meal-planner-api.mpdb :as mpdb]
            [compojure.route :as route]
            [compojure.core :refer [defroutes GET POST]]
            [ring.middleware.json :as json-middleware]))

(mpdb/add-meal {:name "Loco Moco's" :url ""})

(defroutes all-routes
  (GET "/meals" []
    (let [meals (mpdb/list-random-meals 7)
          data (into [] (map #(hash-map :name %) meals))]
      {:status 200
       :body data}))

  (GET "/meal" []
    (let [meal (mpdb/random-meal)]
      {:status 200
       :body (hash-map :name meal)}))


  ;; TODO: validate this better
  (POST "/meal" request
    (let [body (:body request)]
      (if (nil? (:url {:name "something"}))
        (mpdb/add-meal (assoc body :url "nil"))
        (mpdb/add-meal body))
      {:status 200
       :body {:msg "Successfully added"}}))

  (route/not-found "<p>Page not found.</p>")) ;; all other, return 404

(def app
  (-> all-routes
     (json-middleware/wrap-json-body {:keywords? true})
     (json-middleware/wrap-json-response)))

(defonce server (atom nil))

(defn stop-server
  []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn -main
  [& args]
  (stop-server)
  (reset! server (run-server app {:port 3000}))
  (println "Starting server on port 3000."))
