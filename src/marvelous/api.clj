(ns marvelous.api
  [:require
   [clojure.string :as str :only [split]]
   [clj-http.client :as http :only [get]]
   [clojure.data.json :as json :only [read-str]]
   [digest :as digest :only [md5]]])

(def base-url "http://gateway.marvel.com/v1/public/")

(defn- credentials
  []
  (str/split (System/getenv "MARVEL_CREDENTIALS") #"::"))

(defn- epoch []
  (int (/ (System/currentTimeMillis) 1000)))

(defn- credentials-hash
  [& args]
  (digest/md5 (apply str args)))

(defn- fetch-from-api
  "fetches data from Marvel's API"
  [resource limit offset]
  (let [ts (epoch)
        [api-key pvt-key] (credentials)]
    (http/get
     (str base-url resource)
     {
      :query-params
      {
       :limit limit
       :offset offset
       :apikey api-key
       :ts ts
       :hash (credentials-hash ts pvt-key api-key)}})))

(defn- parse
  [s]
  (json/read-str s))

(defn fetch
  [resource limit offset]
  (parse (:body (fetch-from-api resource limit offset))))
