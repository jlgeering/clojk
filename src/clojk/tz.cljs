(ns clojk.tz
  (:require
   [cljs-time.core :as t]
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(defcard show-time
  (sab/html [:div [:div {:class "time"}
             [:span {:class "hours"} "01"]
             [:span ":"]
             [:span {:class "minutes"} "01"]
             [:span ":"]
             [:span {:class "seconds"} "01"]]
            [:div {:class "time"}
             [:span {:class "hours"} "12"]
             [:span ":"]
             [:span {:class "minutes"} "34"]
             [:span ":"]
             [:span {:class "seconds"} "56"]]]))

(defonce state
  (let [a (atom {:time 0})]
    (js/setInterval (fn [] (swap! state update-in [:time] t/now)) 200)
    a))

(defcard state-observer state {} {:history false})

(defcard current-time
  (fn [data-atom _]
    (sab/html (let [now (:time @data-atom)]
                [:div {:class "time"}
                 [:span {:class "hours"} (t/hour now)]
                 [:span ":"]
                 [:span {:class "minutes"} (t/minute now)]
                 [:span ":"]
                 [:span {:class "seconds"} (t/second now)]])))
  state
  {:inspect-data true})
