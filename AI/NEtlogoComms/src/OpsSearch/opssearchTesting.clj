(require '[OpsSearch.opssearch :refer :all])
(require '[clojure.pprint :refer :all])
(require '[OpsSearch.ops :refer :all])
(require '[OpsSearch.worlds :refer :all])
(require '[matcher :refer :all])

(pprint "Testing Performance World1 (1 agent):")
;Test 1
;Testing performance of the ops-search with a single agent. The agents goal for this test is to get to nodeQ.
;40~ msecs to complete. Changing agent to move a few extra nodes should have very minimal effects on performance.
;(time (ops-search world1-test1 '((at R nodeQ)) ops-main1))

;Test 2
;50~ msecs to complete.
;(time (ops-search world1-test1 '((at R nodeK)) ops-main1))

(pprint "Testing Performance World1 (2 agents):")
;Test 3
;Agent R moves from nodeA to nodeQ. Agent A does not need to move. Second operation checks if the presence of a goal
;state for the other agent effects the search.
;403~ msecs to complete. By adding a new agent, time to complete increased by 380~ msecs.
;(time (ops-search world1-test2 '((at R nodeQ)) ops-main1))

;Test 4
;412~ msecs to complete. Defining the position of the second agent does not have a significant impact on the performance.
;(time (ops-search world1-test2 '((at R nodeQ) (at A nodeC)) ops-main1))

;Test 5
;nodeB is right next to nodeC. Testing how an additional required move effects time to complete.
;525~ msecs to complete.
;(time (ops-search world1-test2 '((at R nodeQ) (at A nodeB)) ops-main1))

;Test 6
;1103~ msecs to complete. Testing how multiple required moves on the second agent effect the time to complete.
;(time (ops-search world1-test2 '((at R nodeQ) (at A nodeA)) ops-main1))

;Test 7
;1275~ msecs to complete.
;(time (ops-search world1-test2 '((at R nodeX) (at A nodeR)) ops-main1))

;In comparison to the tests done to world1, the time to complete these tests are substantially higher.



(pprint "Testing Performance World1 (3 agents):")
;Test 8
;The same tests from above are redone, however a single new agent has been added to the world. They will not be required
;to move in any of these tests.
;3410~ msecs to complete. Similar to testing 2 agents. Adding an additional agent that requires no action causes the
;time to complete to be much higher. (By around 2000 msecs this time)
;(time (ops-search world1-test3 '((at R nodeQ)) ops-main1))

;Test 9
;3404~ msecs to complete.
;(time (ops-search world1-test3 '((at R nodeQ) (at A nodeC)) ops-main1))

;Test 10
;nodeB is right next to nodeC. Testing how an additional required move effects time to complete.
;4967~ msecs to complete. Similar to the test done above with the same solution, the time to complete increased, however
;for this test the time to complete was considerably longer.
;(time (ops-search world1-test3 '((at R nodeQ) (at A nodeB)) ops-main1))

;Test 11
;Testing how making both agent A and agent B move to nodeB impacts performance.
;7131~ msecs to complete. Every additional action causes a large dip in performance.
;(time (ops-search world1-test3 '((at R nodeQ) (at A nodeB) (at B nodeB)) ops-main1))

;Test 12
;10,093~ msec to complete. Again, a very large dip in performance.
;(time (ops-search world1-test3 '((at R nodeP) (at A nodeB) (at B nodeB)) ops-main1))


(pprint "Testing Performance World2 (1 agent):")
;Test 13
;Agent R moves from nodeA to nodeD. Picks up key in order to open door at nodeC.
;15.4~ msecs to complete.
;(time (ops-search world2-test1 '((at R nodeD)) ops-main2))

;Test 14
;26.5~ msecs to complete.
;(time (ops-search world2-test1 '((at R nodeA) (unlocked redDoor)) ops-main2))


(pprint "Testing Performance World2 (2 agents):")
;Test 15
;556~ msecs to complete.
;(time (ops-search world2-test2 '((at R nodeA) (at B nodeA)) ops-main2))

(pprint "Testing Performance World3 (1 agent):")
;Test 16
;Agent needs to grab redKey in order to open redDoor to get to nodeR.
;333~ msecs to complete.
;(time (ops-search world3-test1 '((at R nodeR)) ops-main2))

;Test 17
;Agents need to grab the greenKey from nodeR. The agent goes to nodeM (where the green door is) but does not open it.
;3365~ msecs to complete.
;(time (ops-search world3-test1 '((at R nodeM) (holding R greenKey) (unlocked redDoor)) ops-main2))

;Test 18
;The above test is repeated, however the agent opens the green door and then moves to nodeL.
;13,702~ msecs to complete. Very large difference in performace. Is this due to the solution being
;further down the tree than the above test? Or does the trees branching size change due to the additional
;operations?
;(time (ops-search world3-test1 '((at R nodeL)) ops-main2))


(pprint "Testing Performance World3 (2 agents):")
;Test 19
;57,412~ msecs to complete. Agent B does not need to move as they are already at nodeJ.
;(time (ops-search world3-test2 '((at R nodeL) (at B nodeJ)) ops-main2))

;Test 20
;58,060~ msecs to complete. The same operations should occur as abovce, however agent Bs position is not specified.
;(time (ops-search world3-test2 '((at R nodeL)) ops-main2))

;Test 21
;80,077~ msecs to complete. One additional operation (agent B moving from nodeJ to nodeL).
;(time (ops-search world3-test2 '((at R nodeL) (at B nodeL)) ops-main2))

;Test 22
;Is this caused by the fact agent B needs to move? What happens if we add an additional move for only the first agent (R).
;89,787~ msecs to complete. Seems it does not matter which agent needs to make an additional move.
;(time (ops-search world3-test2 '((at R nodeJ) (at B nodeJ)) ops-main2))

;Test 23
;168,274~ msecs to complete. 2 addition operation from first test (agent B moving from J to L then from L to M).
;(time (ops-search world3-test2 '((at R nodeL) (at B nodeM)) ops-main2))

;Test 24
;Agent R needs to open the greenDoor to allow for agentB to move to nodeA. STACK OVERFLOW ERROR DUE TO VERY LARGE TREE.
;(time (ops-search world3-test2 '((at R nodeA) (at B nodeA)) ops-main2))

;Test 25
;Agent R does not need to open the door for this test. But still requires the key in hand.
;14,915~ msecs to complete.
;(time (ops-search world3-test2 '((at R nodeM) (holding R greenKey) (at B nodeJ)) ops-main2))

;Test 26
;This time, agent R opens the door when at M but does not move.
;57,983~ msecs to complete. Why does opening the door cause such a huge performance issue?
;(time (ops-search world3-test2 '((at R nodeM) (unlocked greenDoor) (at B nodeJ)) ops-main2))


(pprint "Testing Performance World4 (1 agent):")
;Test 27
;Testing how the complexitity of the world results in performance changes: Is it drastically different? Or is it very minor?
;221~ msecs to complete. This test and test 1 both required the agent to move 9 times. However, it took 10 times the amount of
;time to solve this search.
;(time(ops-search world4-test1 '((at R node28)) ops-main2))


(pprint "Testing Performance World4 (2 agents):")
;Test 28
;Same test as above however two agents are in the world.
;2885~ msecs to complete.
;(time(ops-search world4-test2 '((at R node28)) ops-main2))

;Test 29
;Agent B is now required to do some movements.
;32,923~ msecs to complete.
;(time(ops-search world4-test2 '((at R node28) (at B node28)) ops-main2))


;Note: This world is so complex that Ops-search has a VERY hard time finding solutions. A large amount
;of tests done resulted in stack overflow errors. This is intentional as we intend to test whether or not other mechanisms
;such as planner or A* search will successfully be able to find solutions.
(pprint "Testing Performance World5 (1 agent):")
;Test 30
;Agent is asked to unlock greenDoor.
;13,045~ msecs to complete.
;(time(ops-search world5-test1 '((unlocked greenDoor)) ops-main3))

;Test 31
;Agent is asked to unlock greenDoor and greenDoor2.
;75,565~ msecs to complete.
;(time(ops-search world5-test1 '((unlocked greenDoor) (unlocked greenDoor2)) ops-main3))

;Test 32
;Agent is asked to unlock greenDoor and greenDoor2 and to have a purple key is its hands.
;128,434~msecs to complete.
;(time(ops-search world5-test1 '((unlocked greenDoor) (unlocked greenDoor2) (holding R purpleKey)) ops-main3))3

;Test 33
;Agent is asked to do test 32, but then move to node50 after.
;226,777~ msecs to complete.
;(time(ops-search world5-test1 '((unlocked greenDoor) (unlocked greenDoor2) (holding R purpleKey) (at R node50)) ops-main3))



;NetLogo Tests

;(ops-searchNL world4-test1 '((at R node56)) ops-main1 setupNetlogoExample3)
