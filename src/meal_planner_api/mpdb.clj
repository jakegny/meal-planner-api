(ns meal-planner-api.mpdb
  (:require [datomic.client.api :as d]
            [meal-planner-api.config :refer [read-config]]))

(def cfg {:server-type :peer-server
          :access-key "myaccesskey"
          :secret "mysecret"
          :endpoint "localhost:8998"})

(def client (d/client cfg))

(def conn (d/connect client {:db-name "meal_planner"}))

(def meal-schema [{:db/ident :meal/name
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc "The name of the meal"}

                  {:db/ident :meal/url
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc "Link to the meal recipe"}])

(d/transact conn {:tx-data meal-schema})

(def first-meals [{:meal/name "Sushi Bowls"
                   :meal/url "asdfasdf"}
                  {:meal/name "Sweet Potato Skillet"
                   :meal/url "https://www.budgetbytes.com/chorizo-sweet-potato-skillet/"}
                  {:meal/name "Burgers"
                   :meal/url "fdafda"}])

; (d/transact conn {:tx-data first-meals})

(def db (d/db conn))

(def all-meals-ids-q '[:find ?e
                       :where [?e :meal/name]])

(def all-meals-names-q '[:find ?meal-name
                         :where [_ :meal/name ?meal-name]])

(defn add-meal
  [meal]
  (d/transact conn {:tx-data [{:meal/name (:name meal)
                               :meal/url (:url meal)}]}))

(defn random-meal
  []
  (let [db (d/db conn)
        meals (d/q all-meals-names-q db)]
    (first (rand-nth meals))))

(defn list-random-meals
  [n]
  (repeatedly n random-meal))
