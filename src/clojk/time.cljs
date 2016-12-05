(ns clojk.time
  (:require
   [cljs-time.core :as t]
   [cljs-time.format :as tf]
   [clojure.string :as str]
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(defn to-local [dt]
  (t/to-default-time-zone dt))

(def custom-formatter (tf/formatter "HH mm ss"))
(defn format-time [dt]
  (tf/unparse custom-formatter dt))

(defn time-vec [dt]
  (str/split (format-time dt) " "))

(defn get-time [dt]
  (let [time (time-vec dt)]
    {:h (get time 0)
     :m (get time 1)
     :s (get time 2)}))

;-------------------------------------------------------------------------------

(defn time-div [dt]
  (let [time (get-time dt)]
    [:div {:class "time"}
     [:span {:class "hours"} (:h time)]
     [:span ":"]
     [:span {:class "minutes"} (:m time)]
     [:span ":"]
     [:span {:class "seconds"} (:s time)]]))

;-------------------------------------------------------------------------------

(defonce state
  (let [a (atom {:time 0})]
    (js/setInterval (fn [] (swap! state update-in [:time] t/now)) 200)
    a))

(defcard state-observer state {} {:history false})

(defcard current-time-utc
  (fn [data-atom _]
    (sab/html (let [now (:time @data-atom)]
                (time-div now))))
  state
  #_{:inspect-data true})

(defcard current-time-local
  (fn [data-atom _]
    (sab/html (let [now (to-local (:time @data-atom))]
                (time-div now))))
  state)