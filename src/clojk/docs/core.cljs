(ns clojk.docs.core
  (:require
   [devcards.core :as dc] ; todo: how to exclude from final build?
   [clojk.digital :as cd]
   [clojk.time :as ct]
   [cljs-time.core :as t]
   [reagent.core :as r]))

(enable-console-print!)
(print "loading")

(defn greeting [message]
  [:h1 message])

(defn simple-example []
  [:div
   [greeting "Hello world, it is now"]
   [cd/clock-component-local]])

(defn ^:export run []
  (r/render [simple-example]
            (js/document.getElementById "app")))

(run)
