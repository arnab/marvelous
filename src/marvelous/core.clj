(ns marvelous.core
  [:require [marvelous.api :as api]
   [marvelous.characters :as characters]
   [marvelous.events :as events]])

(defn plot
  []
  ;; 3 super heroes
  ;; 2 events
  ;; one story
  (let [cs (characters/rand 3)
        es (events/rand 2)]
    (println [cs es])))

#_ (plot)
