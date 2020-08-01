(defproject fixecs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/test.check "0.10.0"]
                 [cheshire "5.10.0"]]

  :profiles {:kaocha {:dependencies [[lambdaisland/kaocha "1.0.632"]]}}

  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner" "--stc-num-tests" 5]}
  :main fixecs.core)