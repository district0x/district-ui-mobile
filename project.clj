(defproject district0x/district-ui-mobile "1.0.0"
  :description "district UI module for mobile integration"
  :url "http://github.com/district0x/district-ui-mobile"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[district0x/district-cljs-utils "1.0.3"]
                 [mount "0.1.13"]
                 [org.clojure/clojurescript "1.10.339"]
                 [re-frame "0.10.5"]]

  :doo {:paths {:karma "./node_modules/karma/bin/karma"}
        :build "tests"
        :alias {:default [:chrome]}}

  :npm {:devDependencies [[karma "3.0.0"]
                          [karma-chrome-launcher "2.2.0"]
                          [karma-cli "1.0.1"]
                          [karma-cljs-test "0.1.0"]]}
  
  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.2"]
                                  [day8.re-frame/test "0.1.5"]
                                  [org.clojure/clojure "1.9.0"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [print-foo-cljs "2.0.3"]]
                   :plugins [[lein-cljsbuild "1.1.7"]
                             [lein-doo "0.1.9"]
                             [lein-npm "0.6.2"]]}}

  :cljsbuild {:builds [{:id "tests"
                        :source-paths ["src" "test"]
                        :compiler {:output-to "target/tests-output/tests.js"
                                   :output-dir "target/tests-output"
                                   :main "district.ui.mobile.test-runner"
                                   :optimizations :none}}]})
