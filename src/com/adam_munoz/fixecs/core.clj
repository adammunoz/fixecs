(ns com.adam-munoz.fixecs.core
 (:gen-class)
 (:require [clojure.spec.alpha :as s]
           [cheshire.core :refer :all]
           [clojure.java.io :as io]
           [com.adam-munoz.fixecs.aws :as aws]))

; Begin Data Specs

(s/def ::name string?)
(s/def ::env-var (s/keys :req-un [::name]))
(s/def ::environment (s/coll-of ::env-var))

(s/def ::ecs-task (s/map-of 
 #{"environment"} ::environment
 :min-count 1))

; End Data Specs

(defn parse-json [path] 
 (-> path
     slurp
     parse-string))

(s/fdef sort-env
 :args (s/cat :j-from-file ::ecs-task
              :env-from-aws ::environment)
 :ret ::environment)

(defn sort-env [j-from-file env-from-aws]
  (let [ks (map :name env-from-aws)]
    (sort-by #(.indexOf ks (% "name")) 
             (j-from-file "environment"))))

(s/fdef merge-env
 :args (s/cat :j-original ::ecs-task
              :j-env ::environment)
 :ret ::ecs-task)

(defn merge-env [j-original j-env]
 (merge j-original {"environment" j-env}))

(s/fdef convert
 :args (s/cat :original ::ecs-task
              :env-from-aws ::environment)
 :ret ::ecs-task)

(defn convert [original env-from-aws]
 (merge-env original
            (sort-env original env-from-aws)))

(defn -main [& args]
 (let [path (first args)
       env-from-aws (aws/get-remote-env "auth")]

  (println "🐯 fixecs:" path)

  (io/copy (io/file path) 
           (io/file (str path ".bak")))

  (spit path
        (generate-string (map #(convert % env-from-aws) 
                              (parse-json path))
                         {:pretty true}))))