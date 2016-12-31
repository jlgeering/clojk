(ns clojk.cards.time
  (:require
   [clojk.time :as nut]
   [cljs-time.core :as t]
   [reagent.core :as r])
  (:require-macros
   [devcards.core :as dc :refer [defcard defcard-rg deftest]]))

;-------------------------------------------------------------------------------

(defonce state
  (let [a (atom {:time 0})]
    (js/setInterval (fn [] (swap! state update-in [:time] t/now)) 200)
    a))

(defcard state-observer state {} {:history false})

(defcard current-time-utc
  (fn [data-atom _]
    (r/as-element (let [now (:time @data-atom)]
                    (nut/time-div now))))
  state
  #_{:inspect-data true})

(defcard current-time-local
  (fn [data-atom _]
    (r/as-element (let [now (nut/to-local (:time @data-atom))]
                    (nut/time-div now))))
  state)

(defcard atom-now nut/now {} {:history false})