(require '[OpsSearch.opssearch :refer :all])
(require '[clojure.pprint :refer :all])
(require '[OpsSearch.ops :refer :all])
(require '[OpsSearch.worlds :refer :all])

(defn findAgentStartingNode [state]
  (mfind* ['((agent ?agent) (at ?agent ?node)) state] (? node)))

(defn messageNL [result]
  (cond (some? (:move result)) (newPoint (:move result))
        (some? (:pickup result)) (pickupItem (:pickup result))
        (some? (:unlock result)) (unlockDoor (:unlock result))
        (some? (:drop result)) (dropItem) ;Currently, agent can only carry one item because of this. For multiple, the item name would need to passed through.
  ))

(defn ops-searchNL [state goal ops setupfn]
  (let [node (findAgentStartingNode state)
        result (ops-search state goal ops)]
  (setupfn node)
  (doall (map messageNL (:cmds result)))
  (:txt result)
  ))