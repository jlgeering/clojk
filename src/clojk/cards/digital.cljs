(ns clojk.cards.digital
  (:require
   [clojk.digital :as nut]
   [cljs-time.core :as t]
   [clojk.time :as ct]
   [reagent.core :as r])
  (:require-macros
   [devcards.core :as dc :refer [defcard defcard-rg deftest]]))

(defcard seven-segment-display
  (r/as-element [:div.segment-display
                 [:svg {:width 375 :height 96 :viewBox "0 0 375 96"}
                  [:g {:transform "translate(0,0)"}
                   (nut/seven-segment-display {} "off")
                   [:g {:transform "translate( 60,0)"} (nut/separator "off")]]]]))

(defcard seven-segment-display-lit
  (r/as-element [:div.segment-display
                 [:svg {:width 375 :height 96 :viewBox "0 0 375 96"}
                  [:g {:transform "translate(0,0)"}
                   (nut/seven-segment-display {} "lit")
                   [:g {:transform "translate( 60,0)"} (nut/separator "lit")]]]]))

(defcard digits
  (r/as-element [:div.segment-display
                 [:svg {:width 600 :height 96 :viewBox "0 0 600 96"}
                  [:g {:transform "translate(  0,0)"} (nut/digit-display \1)]
                  [:g {:transform "translate( 60,0)"} (nut/digit-display \2)]
                  [:g {:transform "translate(120,0)"} (nut/digit-display \3)]
                  [:g {:transform "translate(180,0)"} (nut/digit-display \4)]
                  [:g {:transform "translate(240,0)"} (nut/digit-display \5)]
                  [:g {:transform "translate(300,0)"} (nut/digit-display \6)]
                  [:g {:transform "translate(360,0)"} (nut/digit-display \7)]
                  [:g {:transform "translate(420,0)"} (nut/digit-display \8)]
                  [:g {:transform "translate(480,0)"} (nut/digit-display \9)]
                  [:g {:transform "translate(540,0)"} (nut/digit-display \0)]]]))

(defcard number-strings
  (r/as-element [:div.segment-display
                 [:svg {:width 600 :height 96 :viewBox "0 0 600 96"}
                  (nut/digits-display "0123456789")]]))

(defcard static-clock
  (r/as-element (nut/clock-display "12" "34" "56" "lit")))

(defonce state
  (let [a (atom {:time 0})]
    (js/setInterval (fn [] (swap! state update-in [:time] t/now)) 200)
    a))

(defcard current-time-utc
  (fn [data-atom _]
    (let [now (:time @data-atom)
          time (ct/get-time now)]
      (r/as-element (nut/clock-display (:h time) (:m time) (:s time) "lit"))))
  state
  {:inspect-data true})

(defcard current-time-local
  (fn [data-atom _]
    (let [now (ct/to-local (:time @data-atom))
          time (ct/get-time now)]
      (r/as-element (nut/clock-display (:h time) (:m time) (:s time) "lit"))))
  state)

(defcard-rg clock-component-local
  [nut/clock-component-local])
