(ns clojk.tz
  (:require
   [cljs-time.core :as t]
   [clojk.time :as ct]
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

;-------------------------------------------------------------------------------

(defonce state
  (let [a (atom {:time 0})]
    (js/setInterval (fn [] (swap! state update-in [:time] t/now)) 200)
    a))

(defcard state-observer state {} {:history false})

