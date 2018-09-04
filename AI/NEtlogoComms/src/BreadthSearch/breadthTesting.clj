(require '[BreadthSearch.breadthmaps :refer :all])
(require '[BreadthSearch.breadthfirstsearch :refer :all])

;Not much testing will be done with breadth first search as its current implemenation only allows for 1 agent, and no
;other operations apart from moving.

(pprint "Testing Performance World1 (1 agent):")
;Test 1
;The ops-search version of this test took around 40-50 msecs to complete.
;This test takes around 0.1 msecs to complete.
;(time (breadth-search 'nodeA 'nodeQ bWorldMap))

;;Almost all tests testing this world took around 0.1 to 0.5 msecs to find a route.


(pprint "Testing Performance World2 (1 agent):")
;Test 1
;The ops-search version of this test took around 220 msecs to complete.
;This test takes around 0.1 msecs to complete.
;(time(breadth-search 'node1 'node28 bWorldMap2))

;Almost all tests testing this world took around 0.1 to 0.8 msecs to find a route.

;From these tests, we can see that the implemenation of breadth-first-search finds solutions much quicker than the implementation
;of ops search due to the consideration of world states etc. However breadth-first-search currently does not allow
;for further expansions of the worlds (doors, keys).


;NetLogo tests

;(breadth-searchNL 'node39 'node28 bWorldMap2 setupNetlogoExample3)