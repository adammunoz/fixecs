(ns com.adam-munoz.fixecs.core
 (:gen-class)
 (:require [clojure.spec.alpha :as s]
           [cheshire.core :refer :all]
           [clojure.java.io :as io]))

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
 :args (s/cat :j-from-file ::ecs-task)
 :ret ::environment)

(defn sort-env [j-from-file]
  (sort-by #(% "name")
           (j-from-file "environment")))

(s/fdef merge-env
 :args (s/cat :j-original ::ecs-task
              :j-env ::environment)
 :ret ::ecs-task)

(defn merge-env [j-original j-env]
 (merge j-original {"environment" j-env}))

(s/fdef convert
 :args (s/cat :original ::ecs-task)
 :ret ::ecs-task)

(defn convert [original]
 (merge-env original
            (sort-env original)))

(defn -main [& args]
 (let [path (first args)]

  (println "🐯 fixecs:" path)

  (io/copy (io/file path) 
           (io/file (str path ".bak")))

  (spit path
        (generate-string (map convert (parse-json path))
                         {:pretty true}))))