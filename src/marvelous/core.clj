(ns marvelous.core
  [:require [marvelous.api :as api]])

(defn run
  []
  (api/fetch))

#_ (run)
