(ns ^{:doc "A-star search from Boris."
      :author "SCM"}
AstarSearch.AstarSearch)

(defn A*search
  [start goal LMG & {:keys [get-state get-cost selector debug]
                     :or   {get-state :state
                            get-cost  :cost
                            selector  :undef
                            debug     false}}]
  (let [goal? (if (fn? goal)
                #(when (goal %) %)
                #(when (= % goal) %))
        member? (fn [lis x] (some (partial = x) lis))
        selector (if-not (= selector :undef)        ;it was a key arg
                   selector                         ; se leave it as is, else set it as default
                   (fn [bag] (first (sort-by (comp get-cost first) bag))))]

    (loop [queued `((~start))
           visited nil
           ]
      (if (empty? queued) nil                       ;; fail if (null queued)
                          (let [next (selector queued) ;; select next node
                                state (first next)  ;; filter out path
                                raw-state (get-state state) ;; filter costs, etc
                                ]
                            (when debug (println 'selecting next '=> raw-state))
                            (cond
                              (and (fn? goal) (goal raw-state))     ;; is goal a predicate & goal found
                              (reverse next)                        ;; quit with result

                              (goal? raw-state)     ;; goal found
                              (reverse next)        ;; quit with result

                              :else
                              (if (member? visited raw-state)
                                (recur (remove #(= % next) queued) visited)
                                (let [queued (remove #(= % next) queued)
                                      moves (LMG state)
                                      new-visited (cons raw-state visited)
                                      new-states (map #(cons % next)
                                                      (remove #(member? visited (get-state %))
                                                              moves))
                                      ]
                                  (when debug
                                    (println 'exploring state '=> raw-state
                                             'path next
                                             'moves moves
                                             'new-states new-states
                                             'queued queued
                                             ))

                                  (recur
                                    (concat queued new-states)
                                    new-visited)
                                  ))
                              ))
                          ))))


;;OTHER A*. Source: https://github.com/Macroz/search

;(defn a*
;  "Calculate shortest path with A*.
;
;  Note the heuristic function must be monotonous, increasing and never overestimate
;  (i.e. it must be admissible) to guarantee an optimal result.
;
;  start is the first node.
;  goal?-fn is called for each expanded node to check whether it is a goal
;  distance-fn is called with from and to nodes and should return a numeric distance.
;  heuristic-fn is called with a node and should return a numeric estimated distance to the nearest goal.
;  neighbors-fn is called with the node and should return all the neighbors
;  max-depth is the maximum depth of the path in steps."
;  [start goal?-fn distance-fn heuristic-fn neighbors-fn max-depth]
;  (loop [open (priority-map [start [start] 0] (heuristic-fn start))
;         depth 0]
;    (let [[[node path distance] total] (first open)]
;      (cond (> depth max-depth) false
;            (goal?-fn node) path
;            :else (recur (into (pop open)
;                               (for [neighbor (neighbors-fn node)]
;                                 (let [new-node neighbor
;                                       new-path (conj path neighbor)
;                                       new-distance (+ distance (distance-fn node neighbor))]
;                                   [[new-node new-path new-distance]
;                                    (+ new-distance (heuristic-fn neighbor))])))
;                         (count path))))))
;
;(defn dijkstra
;  "Calculate shortest path with Dijkstra's algorithm.
;
;  start is the first node.
;  goal?-fn is called for each expanded node to check whether it is a goal
;  distance-fn is called with from and to nodes and should return a numeric distance.
;  neighbors-fn is called with the node and should return all the neighbors
;  max-depth is the maximum depth of the path in steps."
;  [start goal?-fn distance-fn neighbors-fn max-depth]
;  ;; Dijkstra is a special case of A* when the heuristic is zero
;  (a* start goal?-fn distance-fn (constantly 0) neighbors-fn max-depth))