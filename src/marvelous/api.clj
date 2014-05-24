(ns marvelous.api
  [:require
   [clj-http.client :as http]
   [clojure.data.json :as json]])

(def base-url "http://gateway.marvel.com:80/v1/public/")

(defn- fetch-from-api
  "fetches data from Marvel's API"
  [limit offset]
  (http/get
   (str base-url "characters") {
    :query-params {
     :limit limit
     :offset offset
     :apikey "552081f369860d155d50745c9688e1c8"
     :ts "1"
     :hash "a9b60cc028691a98ad8553782dec9580"}}))

(defn- parse
  [s]
  (json/read-str s))

(defn fetch
  [limit offset]
  (parse (:body (fetch-from-api limit offset))))
