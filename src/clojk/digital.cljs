(ns clojk.digital
  (:require
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(defn seven-segment-display [class segment-classes segment-default]
  [:g {:class class}
   [:path {:class ["segment" "segment-a" (:a segment-classes segment-default)] :d "M10,8L14,4L42,4L46,8L42,12L14,12L10,8z"}]
   [:path {:class ["segment" "segment-b" (:b segment-classes segment-default)] :d "M48,10L52,14L52,42L48,46L44,42L44,14L48,10z"}]
   [:path {:class ["segment" "segment-c" (:c segment-classes segment-default)] :d "M48,50L52,54L52,82L48,86L44,82L44,54L48,50z"}]
   [:path {:class ["segment" "segment-d" (:d segment-classes segment-default)] :d "M10,88L14,84L42,84L46,88L42,92L14,92L10,88z"}]
   [:path {:class ["segment" "segment-e" (:e segment-classes segment-default)] :d "M8,50L12,54L12,82L8,86L4,82L4,54L8,50z"}]
   [:path {:class ["segment" "segment-f" (:f segment-classes segment-default)] :d "M8,10L12,14L12,42L8,46L4,42L4,14L8,10z"}]
   [:path {:class ["segment" "segment-g" (:g segment-classes segment-default)] :d "M10,48L14,44L42,44L46,48L42,52L14,52L10,48z"}]])

(def digit-to-classes
  {1 {:a "off" :b "lit" :c "lit" :d "off" :e "off" :f "off" :g "off"}
   2 {:a "lit" :b "lit" :c "off" :d "lit" :e "lit" :f "off" :g "lit"}
   3 {:a "lit" :b "lit" :c "lit" :d "lit" :e "off" :f "off" :g "lit"}
   4 {:a "off" :b "lit" :c "lit" :d "off" :e "off" :f "lit" :g "lit"}
   5 {:a "lit" :b "off" :c "lit" :d "lit" :e "off" :f "lit" :g "lit"}
   6 {:a "lit" :b "off" :c "lit" :d "lit" :e "lit" :f "lit" :g "lit"}
   7 {:a "lit" :b "lit" :c "lit" :d "off" :e "off" :f "off" :g "off"}
   8 {:a "lit" :b "lit" :c "lit" :d "lit" :e "lit" :f "lit" :g "lit"}
   9 {:a "lit" :b "lit" :c "lit" :d "lit" :e "off" :f "lit" :g "lit"}
   0 {:a "lit" :b "lit" :c "lit" :d "lit" :e "lit" :f "lit" :g "off"}})

(defn digit-display [class digit]
  (seven-segment-display class (get digit-to-classes digit) nil))

(defcard seven-segment-display
  (sab/html [:div {:class "segment-display"}
             [:svg {:width 375 :height 96 :viewBox "0 0 375 96"}
              [:g {:transform "translate(17,0)"}
               (seven-segment-display "ssd" {} "off")]]]))

(defcard seven-segment-display-lit
  (sab/html [:div {:class "segment-display"}
             [:svg {:width 375 :height 96 :viewBox "0 0 375 96"}
              [:g {:transform "translate(17,0)"}
               (seven-segment-display "ssd" {} "lit")]]]))

(defcard digits
  (sab/html [:div {:class "segment-display"}
             [:svg {:width 600 :height 96 :viewBox "0 0 600 96"}
              [:g {:transform "translate(  0,0)"} (digit-display "ssd" 1)]
              [:g {:transform "translate( 60,0)"} (digit-display "ssd" 2)]
              [:g {:transform "translate(120,0)"} (digit-display "ssd" 3)]
              [:g {:transform "translate(180,0)"} (digit-display "ssd" 4)]
              [:g {:transform "translate(240,0)"} (digit-display "ssd" 5)]
              [:g {:transform "translate(300,0)"} (digit-display "ssd" 6)]
              [:g {:transform "translate(360,0)"} (digit-display "ssd" 7)]
              [:g {:transform "translate(420,0)"} (digit-display "ssd" 8)]
              [:g {:transform "translate(480,0)"} (digit-display "ssd" 9)]
              [:g {:transform "translate(540,0)"} (digit-display "ssd" 0)]]]))


; <svg >
;   <g transform="translate(17,0)">
;     <g class="digit" transform="skewX(-12)">
;       <path d="M66,8L70,4L98,4L102,8L98,12L70,12L66,8z"/>
;       <path d="M64,10L68,14L68,42L64,46L60,42L60,14L64,10z"/>
;       <path d="M104,10L108,14L108,42L104,46L100,42L100,14L104,10z"/>
;       <path d="M66,48L70,44L98,44L102,48L98,52L70,52L66,48z"/>
;       <path d="M64,50L68,54L68,82L64,86L60,82L60,54L64,50z"/>
;       <path d="M104,50L108,54L108,82L104,86L100,82L100,54L104,50z"/>
;       <path d="M66,88L70,84L98,84L102,88L98,92L70,92L66,88z"/>
;     </g>
;     <g class="separator">
;       <circle r="4" cx="112" cy="28"/>
;       <circle r="4" cx="103.5" cy="68"/>
;     </g>
;     <g class="digit" transform="skewX(-12)">
;       <path d="M134,8L138,4L166,4L170,8L166,12L138,12L134,8z"/>
;       <path d="M132,10L136,14L136,42L132,46L128,42L128,14L132,10z"/>
;       <path d="M172,10L176,14L176,42L172,46L168,42L168,14L172,10z"/>
;       <path d="M134,48L138,44L166,44L170,48L166,52L138,52L134,48z"/>
;       <path d="M132,50L136,54L136,82L132,86L128,82L128,54L132,50z"/>
;       <path d="M172,50L176,54L176,82L172,86L168,82L168,54L172,50z"/>
;       <path d="M134,88L138,84L166,84L170,88L166,92L138,92L134,88z"/>
;     </g>
;     <g class="digit" transform="skewX(-12)">
;       <path d="M190,8L194,4L222,4L226,8L222,12L194,12L190,8z"/>
;       <path d="M188,10L192,14L192,42L188,46L184,42L184,14L188,10z"/>
;       <path d="M228,10L232,14L232,42L228,46L224,42L224,14L228,10z"/>
;       <path d="M190,48L194,44L222,44L226,48L222,52L194,52L190,48z"/>
;       <path d="M188,50L192,54L192,82L188,86L184,82L184,54L188,50z"/>
;       <path d="M228,50L232,54L232,82L228,86L224,82L224,54L228,50z"/>
;       <path d="M190,88L194,84L222,84L226,88L222,92L194,92L190,88z"/>
;     </g>
;     <g class="separator">
;       <circle r="4" cx="236" cy="28"/>
;       <circle r="4" cx="227.5" cy="68"/>
;     </g>
;     <g class="digit" transform="skewX(-12)">
;       <path d="M258,8L262,4L290,4L294,8L290,12L262,12L258,8z"/>
;       <path d="M256,10L260,14L260,42L256,46L252,42L252,14L256,10z"/>
;       <path d="M296,10L300,14L300,42L296,46L292,42L292,14L296,10z"/>
;       <path d="M258,48L262,44L290,44L294,48L290,52L262,52L258,48z"/>
;       <path d="M256,50L260,54L260,82L256,86L252,82L252,54L256,50z"/>
;       <path d="M296,50L300,54L300,82L296,86L292,82L292,54L296,50z"/>
;       <path d="M258,88L262,84L290,84L294,88L290,92L262,92L258,88z"/>
;     </g>
;     <g class="digit" transform="skewX(-12)">
;       <path d="M314,8L318,4L346,4L350,8L346,12L318,12L314,8z"/>
;       <path d="M312,10L316,14L316,42L312,46L308,42L308,14L312,10z"/>
;       <path d="M352,10L356,14L356,42L352,46L348,42L348,14L352,10z"/>
;       <path d="M314,48L318,44L346,44L350,48L346,52L318,52L314,48z"/>
;       <path d="M312,50L316,54L316,82L312,86L308,82L308,54L312,50z"/>
;       <path d="M352,50L356,54L356,82L352,86L348,82L348,54L352,50z"/>
;       <path d="M314,88L318,84L346,84L350,88L346,92L318,92L314,88z"/>
;     </g>
;   </g>
; </svg>
