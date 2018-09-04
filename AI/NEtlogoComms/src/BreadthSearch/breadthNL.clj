(require '[BreadthSearch.breadthmaps :refer :all])
(require '[BreadthSearch.breadthfirstsearch :refer :all])

(defn breadth-searchNL [start goal bmap setupfn]
  (let [result (breadth-search start goal bmap)]
  (setupfn start)
  (doall (map newPoint result))
  result))