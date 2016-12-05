(ns clojk.time
  (:require
   [cljs-time.core :as t]
   [cljs-time.format :as tf]
   [clojure.string :as str]))

(def custom-formatter (tf/formatter "HH mm ss"))
(defn format-time [date]
  (tf/unparse custom-formatter date))

(defn time-vec [date]
  (str/split (format-time date) " "))

(defn get-time [date]
  (let [time (time-vec date)]
    {:h (get time 0)
     :m (get time 1)
     :s (get time 2)}))
