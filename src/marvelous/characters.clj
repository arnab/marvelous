(ns marvelous.characters
  [:require [marvelous.api :as api]])

(def max-chars-count 1394)

(defn rand
  []
  (api/fetch 1 (rand-int max-chars-count)))

#_ (rand)
