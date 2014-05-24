(ns marvelous.characters
  [:require [marvelous.api :as api]])

(def max-chars-count 1393)

(defn- name-of
  [response]
  (let [c (first ((response "data") "results"))]
    (c "name")))

(defn rand
  [n]
  (let [offsets (take n (repeatedly #(rand-int max-chars-count)))
        responses (map #(api/fetch "characters" 1 %) offsets)]
    (map name-of responses)))

#_ (rand 3)
