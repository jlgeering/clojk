(ns clojk.tz
  (:require
   [cljs-time.core :as t]
   [clojk.time :as ct]
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

(defcard current-time-utc
  (fn [data-atom _]
    (sab/html (let [now (:time @data-atom)
                    time (ct/get-time now)]
                [:div {:class "time"}
                 [:span {:class "hours"} (:h time)]
                 [:span ":"]
                 [:span {:class "minutes"} (:m time)]
                 [:span ":"]
                 [:span {:class "seconds"} (:s time)]])))
  state
  {:inspect-data true})
