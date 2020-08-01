(ns com.adam-munoz.fixecs.aws
 (:require [clojure.spec.alpha :as s]
           [amazonica.aws.ecs :as ecs]))

(defn get-remote-env [task-name]
 (-> (ecs/describe-task-definition :task-definition task-name)
    :task-definition
    :container-definitions
    first
    :environment))
