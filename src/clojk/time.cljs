(ns clojk.time
  (:require
   [cljs-time.core :as t]
   [cljs-time.format :as tf]
   [clojure.string :as str]))

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
