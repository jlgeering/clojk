(ns clojk.cards.tz
  (:require
   [clojk.tz :as nut]
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
