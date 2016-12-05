(ns clojk.digital
  (:require
   [cljs-time.core :as t]
   [clojk.time :as ct]
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(defn seven-segment-display [segment-classes segment-default]
  [:g {:class "segments"}
   [:path {:class ["segment" "segment-a" (:a segment-classes segment-default)] :d "M10,8L14,4L42,4L46,8L42,12L14,12L10,8z"}]
   [:path {:class ["segment" "segment-b" (:b segment-classes segment-default)] :d "M48,10L52,14L52,42L48,46L44,42L44,14L48,10z"}]
   [:path {:class ["segment" "segment-c" (:c segment-classes segment-default)] :d "M48,50L52,54L52,82L48,86L44,82L44,54L48,50z"}]
   [:path {:class ["segment" "segment-d" (:d segment-classes segment-default)] :d "M10,88L14,84L42,84L46,88L42,92L14,92L10,88z"}]
   [:path {:class ["segment" "segment-e" (:e segment-classes segment-default)] :d "M8,50L12,54L12,82L8,86L4,82L4,54L8,50z"}]
   [:path {:class ["segment" "segment-f" (:f segment-classes segment-default)] :d "M8,10L12,14L12,42L8,46L4,42L4,14L8,10z"}]
   [:path {:class ["segment" "segment-g" (:g segment-classes segment-default)] :d "M10,48L14,44L42,44L46,48L42,52L14,52L10,48z"}]])

(defn separator [separator-classes]
  [:g {:class ["separator" separator-classes]}
   [:circle {:r 4 :cx 0 :cy 28}]
   [:circle {:r 4 :cx 0 :cy 68}]])

(def digit-to-classes
  {\1 {:a "off" :b "lit" :c "lit" :d "off" :e "off" :f "off" :g "off"}
   \2 {:a "lit" :b "lit" :c "off" :d "lit" :e "lit" :f "off" :g "lit"}
   \3 {:a "lit" :b "lit" :c "lit" :d "lit" :e "off" :f "off" :g "lit"}
   \4 {:a "off" :b "lit" :c "lit" :d "off" :e "off" :f "lit" :g "lit"}
   \5 {:a "lit" :b "off" :c "lit" :d "lit" :e "off" :f "lit" :g "lit"}
   \6 {:a "lit" :b "off" :c "lit" :d "lit" :e "lit" :f "lit" :g "lit"}
   \7 {:a "lit" :b "lit" :c "lit" :d "off" :e "off" :f "off" :g "off"}
   \8 {:a "lit" :b "lit" :c "lit" :d "lit" :e "lit" :f "lit" :g "lit"}
   \9 {:a "lit" :b "lit" :c "lit" :d "lit" :e "off" :f "lit" :g "lit"}
   \0 {:a "lit" :b "lit" :c "lit" :d "lit" :e "lit" :f "lit" :g "off"}})

(defn digit-display [digit]
  (seven-segment-display (get digit-to-classes digit) nil))

(defn digits-display [digits]
  (map-indexed
   (fn [idx itm]
     [:g {:key idx :transform (str "translate(" (* 60 idx) ",0)")}
      (digit-display itm)])
   (str digits)))

(defn clock-display [h m s sep]
  (sab/html [:div {:class "segment-display"}
             [:svg {:width 410 :height 96 :viewBox "0 0 410 96"}
              [:g {:transform "skewX(-9)"}
               [:g {:transform "translate( 15,0)"} (digits-display h)]
               [:g {:transform "translate(143,0)"} (separator sep)]
               [:g {:transform "translate(155,0)"} (digits-display m)]
               [:g {:transform "translate(283,0)"} (separator sep)]
               [:g {:transform "translate(295,0)"} (digits-display s)]]]]))

; ----------------------------------------------------

(defcard seven-segment-display
  (sab/html [:div {:class "segment-display"}
             [:svg {:width 375 :height 96 :viewBox "0 0 375 96"}
              [:g {:transform "translate(0,0)"}
               (seven-segment-display {} "off")
               [:g {:transform "translate( 60,0)"} (separator "off")]]]]))

(defcard seven-segment-display-lit
  (sab/html [:div {:class "segment-display"}
             [:svg {:width 375 :height 96 :viewBox "0 0 375 96"}
              [:g {:transform "translate(0,0)"}
               (seven-segment-display {} "lit")
               [:g {:transform "translate( 60,0)"} (separator "lit")]]]]))

(defcard digits
  (sab/html [:div {:class "segment-display"}
             [:svg {:width 600 :height 96 :viewBox "0 0 600 96"}
              [:g {:transform "translate(  0,0)"} (digit-display \1)]
              [:g {:transform "translate( 60,0)"} (digit-display \2)]
              [:g {:transform "translate(120,0)"} (digit-display \3)]
              [:g {:transform "translate(180,0)"} (digit-display \4)]
              [:g {:transform "translate(240,0)"} (digit-display \5)]
              [:g {:transform "translate(300,0)"} (digit-display \6)]
              [:g {:transform "translate(360,0)"} (digit-display \7)]
              [:g {:transform "translate(420,0)"} (digit-display \8)]
              [:g {:transform "translate(480,0)"} (digit-display \9)]
              [:g {:transform "translate(540,0)"} (digit-display \0)]]]))

(defcard number-strings
  (sab/html [:div {:class "segment-display"}
             [:svg {:width 600 :height 96 :viewBox "0 0 600 96"}
              (digits-display "0123456789")]]))

(defcard static-clock
  (clock-display "12" "34" "56" "lit"))

(defonce state
  (let [a (atom {:time 0})]
    (js/setInterval (fn [] (swap! state update-in [:time] t/now)) 200)
    a))

(defcard current-time-utc
  (fn [data-atom _]
    (let [now (:time @data-atom)
          time (ct/get-time now)]
      (clock-display (:h time) (:m time) (:s time) "lit")))
  state
  {:inspect-data true})

(defcard current-time-local
  (fn [data-atom _]
    (let [now (ct/to-local (:time @data-atom))
          time (ct/get-time now)]
      (clock-display (:h time) (:m time) (:s time) "lit")))
  state)
