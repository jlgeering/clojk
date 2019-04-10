(defproject clojk "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [com.andrewmcveigh/cljs-time "0.4.0"]
                 [devcards "0.2.2"]
                 [reagent "0.6.0"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            ; [lein-cljsbuild "1.1.4" :exclusions [org.clojure/clojure]]
            [lein-figwheel "0.5.8"]
            [lein-sass "0.4.0"]]

  ; todo add docs/js/compiled ?
  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]

  :source-paths ["src"]

  :sass {:src "resources/sass"
         :output-directory "resources/public/css"

         ;; other options (provided are default values):
         ;; :delete-output-dir true
         ;; :source-maps true
         ;; :style :nested
         ;; :command :sassc (:sass or :sassc are recognized values)
         }
  
  :cljsbuild {
              :builds [{:id "devcards"
                        :source-paths ["src"]
                        :figwheel { :devcards true } ;; <- note this
                        :compiler { :main       "clojk.cards.index"
                                    :asset-path "js/compiled/devcards_out"
                                    :output-to  "resources/public/js/compiled/clojk_devcards.js"
                                    :output-dir "resources/public/js/compiled/devcards_out"
                                    :source-map-timestamp true}}
                       {:id "docs-index"
                        :source-paths ["src"]
                        :compiler { :main "clojk.docs.core"
                                    :asset-path "js/compiled/out"
                                    :output-to  "docs/js/compiled/clojk.js"
                                    :output-dir "target/docs/out"
                                    :optimizations :advanced}}
                       {:id "docs-devcards"
                        :source-paths ["src"]
                        :compiler { :main "clojk.docs.devcards"
                                    :devcards true
                                    :asset-path "js/compiled/cards"
                                    :output-to  "docs/js/compiled/clojk_devcards.js"
                                    :output-dir "target/docs/cards"
                                    :optimizations :advanced}}
                       {:id "dev"
                        :source-paths ["src"]
                        :figwheel true
                        :compiler {:main       "clojk.core"
                                   :asset-path "js/compiled/out"
                                   :output-to  "resources/public/js/compiled/clojk.js"
                                   :output-dir "resources/public/js/compiled/out_dev"
                                   :source-map-timestamp true}}
                       {:id "prod"
                        :source-paths ["src"]
                        :compiler {:main       "clojk.core"
                                   :asset-path "js/compiled/out"
                                   :output-to  "resources/public/js/compiled/clojk.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :optimizations :advanced}}]}

  :figwheel { :css-dirs ["resources/public/css"]}

  ; :aliases {"build-docs" ["do" "clean" ["cljsbuild" "once" "docs-index"] ["cljsbuild" "once" "docs-devcards"]]}
  :aliases {"build-docs" ["cljsbuild" "once" "docs-index" "docs-devcards"]}
  
  ; :cljsbuild {:builds {:app
  ;                      {:source-paths ["src" "env/dev/cljs"]
  ;                       :compiler
  ;                       {:main "reagent-frontend-demo.dev"
  ;                        :output-to "public/js/app.js"
  ;                        :output-dir "public/js/out"
  ;                        :asset-path   "js/out"
  ;                        :source-map true
  ;                        :optimizations :none
  ;                        :pretty-print  true}}
  ;                      :release
  ;                      {:source-paths ["src" "env/prod/cljs"]
  ;                       :compiler
  ;                       {:output-to "public/js/app.js"
  ;                        :output-dir "public/js/release"
  ;                        :asset-path   "js/out"
  ;                        :optimizations :advanced
  ;                        :pretty-print false}}}}

  ; https://github.com/emezeske/lein-cljsbuild/blob/1.1.5/sample.project.clj
  ; :cljsbuild {
  ;   :builds [{
  ;       ; The path to the top-level ClojureScript source directory:
  ;       :source-paths ["src-cljs"]
  ;       ; The standard ClojureScript compiler options:
  ;       ; (See the ClojureScript compiler documentation for details.)
  ;       :compiler {
  ;         :output-to "war/javascripts/main.js"  ; default: target/cljsbuild-main.js
  ;         :optimizations :whitespace
  ;         :pretty-print true}}]}

  ; :figwheel {:http-server-root "public"
  ;            :nrepl-port 7002
  ;            :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
  ;            :css-dirs ["public/css"]}
  ; :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
  ;            ;; :server-port 3449 ;; default
  ;            ;; :server-ip "127.0.0.1"
  ; 
  ;            :css-dirs ["resources/public/css"] ;; watch and update CSS
  ; 
  ;            ;; Start an nREPL server into the running figwheel process
  ;            ;; :nrepl-port 7888
  ; 
  ;            ;; Server Ring Handler (optional)
  ;            ;; if you want to embed a ring handler into the figwheel http-kit
  ;            ;; server, this is for simple ring servers, if this
  ; 
  ;            ;; doesn't work for you just run your own server :) (see lein-ring)
  ; 
  ;            ;; :ring-handler hello_world.server/handler
  ; 
  ;            ;; To be able to open files in your editor from the heads up display
  ;            ;; you will need to put a script on your path.
  ;            ;; that script will have to take a file path and a line number
  ;            ;; ie. in  ~/bin/myfile-opener
  ;            ;; #! /bin/sh
  ;            ;; emacsclient -n +$2 $1
  ;            ;;
  ;            ;; :open-file-command "myfile-opener"
  ; 
  ;            ;; if you are using emacsclient you can just use
  ;            ;; :open-file-command "emacsclient"
  ; 
  ;            ;; if you want to disable the REPL
  ;            ;; :repl false
  ; 
  ;            ;; to configure a different figwheel logfile path
  ;            ;; :server-logfile "tmp/logs/figwheel-logfile.log"
  ;            }

  ; :profiles {:dev {:dependencies [[figwheel-sidecar "0.5.8"]
  ;                                 [org.clojure/tools.nrepl "0.2.12"]
  ;                                 [com.cemerick/piggieback "0.2.1"]]}}
  )
