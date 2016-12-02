(ns clojk.tz
  (:require
   [cljs-time.core :as t]
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(defcard show-time
  (sab/html [:div {:class "time"}
             [:span {:class "hours"} "12"]
             [:span ":"]
             [:span {:class "minutes"} "34"]
             [:span ":"]
             [:span {:class "seconds"} "56"]]))

(defcard current-time
  (sab/html (let [now (t/now)]
              [:div {:class "time"}
               [:span {:class "hours"} (t/hour now)]
               [:span ":"]
               [:span {:class "minutes"} (t/minute now)]
               [:span ":"]
               [:span {:class "seconds"} (t/second now)]])))
