(ns ^{:doc "World maps used by breadth-first-search."
      :author "DGM"}
AstarSearch.AstarLMG)

(require '[matcher :refer :all])
(require '[OpsSearch.opssearch :refer :all])
(require '[OpsSearch.ops :refer :all])

(defn a*lmg2 [state]
  (apply-all ops-main1 state '#{})
  )

(defn goalCheck [state goal]
  (mfind* [goal state] goal)
  )