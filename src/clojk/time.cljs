(ns clojk.time
  (:require
   [cljs-time.core :as t]
   [cljs-time.format :as tf]
   [clojure.string :as str]
   [reagent.core :as r]))

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
    [:div.time
     [:span.hours (:h time)]
     [:span ":"]
     [:span.minutes (:m time)]
     [:span ":"]
     [:span.seconds (:s time)]]))

;-------------------------------------------------------------------------------

; (defonce now
;   (let [a (r/atom (t/now))]
;     (js/setInterval #(reset! a (t/now)) 200)
;     a))

; same as above
(defonce now (r/atom (t/now)))
(defonce now-updater (js/setInterval #(reset! now (t/now)) 200))

