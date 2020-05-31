(ns fixecs.core
 (:gen-class)
 (:require [cheshire.core :refer :all]
           [clojure.java.io :as io]))

(defn parse-json [path] 
 (-> path
     slurp
     parse-string))

(defn sort-env [j-from-file]
  (sort-by #(% "name")
           (j-from-file "environment")))

(defn merge-env [j-original j-env]
 (merge j-original {"environment" j-env}))

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
