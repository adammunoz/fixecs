(defproject com.adam-munoz/fixecs "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/test.check "0.10.0"]
                 [cheshire "5.10.0"]
                 [amazonica "0.3.152"]]

  :profiles {:kaocha {:dependencies [[lambdaisland/kaocha "1.0.632"]]}
             :uberjar {:aot :all}}

  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner" "--stc-num-tests" 5]}
  :main com.adam-munoz.fixecs.core)