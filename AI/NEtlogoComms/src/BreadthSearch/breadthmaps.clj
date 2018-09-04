(ns ^{:doc "World maps used by breadth-first-search."
      :author "DGM"}
BreadthSearch.breadthmaps)

(def bWorldMap
  '{nodeA #{ nodeF }
    nodeB #{ nodeC nodeE }
    nodeC #{ nodeB nodeH }
    nodeD #{ nodeE nodeG }
    nodeE #{ nodeB nodeD }
    nodeF #{ nodeA nodeG nodeN }
    nodeG #{ nodeD nodeF }
    nodeH #{ nodeC nodeI }
    nodeI #{ nodeH nodeM }
    nodeJ #{ nodeK nodeL }
    nodeK #{ nodeJ }
    nodeL #{ nodeJ nodeM }
    nodeM #{ nodeI nodeL nodeQ }
    nodeN #{ nodeF nodeO }
    nodeO #{ nodeN nodeS }
    nodeP #{ nodeQ }
    nodeQ #{ nodeM nodeP nodeW }
    nodeR #{ nodeS }
    nodeS #{ nodeO nodeR nodeT }
    nodeT #{ nodeS nodeU }
    nodeU #{ nodeT nodeV }
    nodeV #{ nodeU nodeW nodeX }
    nodeW #{ nodeQ nodeV }
    nodeX #{ nodeV }
    })

(def bWorldMap2
  '{node1 #{ node9 }
    node2 #{ node3 }
    node3 #{ node2 node4 node12 }
    node4 #{ node3 node5 node20 }
    node5 #{ node4 node13 }
    node6 #{ node7 node14 }
    node7 #{ node6 node8 node22 }
    node8 #{ node7 node15 }
    node9 #{ node1 node10 }
    node10 #{ node9 node11 node17 }
    node11 #{ node10 node12 node18 }
    node12 #{ node3 node11 }
    node13 #{ node5 node14 }
    node14 #{ node6 node13 }
    node15 #{ node8 }
    node16 #{ node17 }
    node17 #{ node10 node16 }
    node18 #{ node11 node19 node26 }
    node19 #{ node18 }
    node20 #{ node4 node21 node27 }
    node21 #{ node20 node22 node30 }
    node22 #{ node7 node21 node23 }
    node23 #{ node22 node32 }
    node24 #{ node25 }
    node25 #{ node24 node26 node34 }
    node26 #{ node18 node25 }
    node27 #{ node20 node28 }
    node28 #{ node27 node46 }
    node29 #{ node30 node37 }
    node30 #{ node21 node29 }
    node31 #{ node32 node39 }
    node32 #{ node23 node31 }
    node33 #{ node34 }
    node34 #{ node25 node33 node35 }
    node35 #{ node34 node36 node44 }
    node36 #{ node35 node45 }
    node37 #{ node29 node38 node51 }
    node38 #{ node37 node39 node56 }
    node39 #{ node31 node38 node40 }
    node40 #{ node39 node41 }
    node41 #{ node40 node48 }
    node42 #{ node43 node52 }
    node43 #{ node42 node44 node49 }
    node44 #{ node35 node43 }
    node45 #{ node36 node46 }
    node46 #{ node28 node45 }
    node47 #{ node48 node57 }
    node48 #{ node41 node47 }
    node49 #{ node43 node50 }
    node50 #{ node49 node51 }
    node51 #{ node37 node50 }
    node52 #{ node42 node53 }
    node53 #{ node50 node52 node54 }
    node54 #{ node53 }
    node55 #{ node56 }
    node56 #{ node38 node55 }
    node57 #{ node47 node58 }
    node58 #{ node57 }
    })