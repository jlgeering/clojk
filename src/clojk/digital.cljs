(ns clojk.digital
  (:require
   [clojk.time :as ct]))

(defn seven-segment-display [segment-classes segment-default]
  [:g {:class "segments"}
   [:path.segment.segment-a {:class (:a segment-classes segment-default) :d "M10,8L14,4L42,4L46,8L42,12L14,12L10,8z"}]
   [:path.segment.segment-b {:class (:b segment-classes segment-default) :d "M48,10L52,14L52,42L48,46L44,42L44,14L48,10z"}]
   [:path.segment.segment-c {:class (:c segment-classes segment-default) :d "M48,50L52,54L52,82L48,86L44,82L44,54L48,50z"}]
   [:path.segment.segment-d {:class (:d segment-classes segment-default) :d "M10,88L14,84L42,84L46,88L42,92L14,92L10,88z"}]
   [:path.segment.segment-e {:class (:e segment-classes segment-default) :d "M8,50L12,54L12,82L8,86L4,82L4,54L8,50z"}]
   [:path.segment.segment-f {:class (:f segment-classes segment-default) :d "M8,10L12,14L12,42L8,46L4,42L4,14L8,10z"}]
   [:path.segment.segment-g {:class (:g segment-classes segment-default) :d "M10,48L14,44L42,44L46,48L42,52L14,52L10,48z"}]])

(defn separator [separator-classes]
  [:g.separator {:class separator-classes}
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
  [:div {:class "segment-display"}
   [:svg {:width 410 :height 96 :viewBox "0 0 410 96"}
    [:g {:transform "skewX(-9)"}
     [:g {:transform "translate( 15,0)"} (digits-display h)]
     [:g {:transform "translate(143,0)"} (separator sep)]
     [:g {:transform "translate(155,0)"} (digits-display m)]
     [:g {:transform "translate(283,0)"} (separator sep)]
     [:g {:transform "translate(295,0)"} (digits-display s)]]]])

(defn clock-component-local []
  (let [local (ct/to-local @ct/now)
        time (ct/get-time local)]
    [clock-display (:h time) (:m time) (:s time) "lit"]))
