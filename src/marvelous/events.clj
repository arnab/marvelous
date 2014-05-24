(ns marvelous.events
  [:require [marvelous.api :as api]])

(def max-events-count 60)

(defn- description-of
  [response]
  (let [c (first ((response "data") "results"))]
    (c "description")))

(defn rand
  [n]
  (let [offsets (take n (repeatedly #(rand-int max-events-count)))
        responses (map #(api/fetch "events" 1 %) offsets)]
    (map description-of responses)))

#_ (rand 2)
