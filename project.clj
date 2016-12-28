(defproject clojk "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.5.3"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [com.andrewmcveigh/cljs-time "0.4.0"]
                 [devcards "0.2.2"]
                 [reagent "0.6.0"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            ; [lein-cljsbuild "1.1.4" :exclusions [org.clojure/clojure]]
            [lein-figwheel "0.5.8"]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]

  :source-paths ["src"]

  :cljsbuild {
              :builds [{:id "devcards"
                        :source-paths ["src"]
                        :figwheel { :devcards true} ;; <- note this
                        :compiler { :main       "clojk.core"
                                    :asset-path "js/compiled/devcards_out"
                                    :output-to  "resources/public/js/compiled/clojk_devcards.js"
                                    :output-dir "resources/public/js/compiled/devcards_out"
                                    :source-map-timestamp true}}
                       {:id "gh-pages"
                        :source-paths ["src"]
                        :compiler { :main "clojk.gh-pages"
                                    :devcards true
                                    :asset-path "js/compiled/cards"
                                    :output-to  "docs/js/compiled/clojk.js"
                                    :optimizations :advanced}}
                       {:id "dev"
                        :source-paths ["src"]
                        :figwheel true
                        :compiler {:main       "clojk.core"
                                   :asset-path "js/compiled/out"
                                   :output-to  "resources/public/js/compiled/clojk.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :source-map-timestamp true}}
                       {:id "prod"
                        :source-paths ["src"]
                        :compiler {:main       "clojk.core"
                                   :asset-path "js/compiled/out"
                                   :output-to  "resources/public/js/compiled/clojk.js"
                                   :optimizations :advanced}}]}

  :figwheel { :css-dirs ["resources/public/css"]})
