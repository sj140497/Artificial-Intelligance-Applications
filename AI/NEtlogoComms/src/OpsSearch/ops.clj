(ns ^{:doc "Operators required for ops-search"
      :author "DGM"}
OpsSearch.ops)

(def ops-main1
  '{
    move {
          :pre ((agent ?agent)
                 (at ?agent ?node1)
                 (connects ?node1 ?node2)
                 )
          :add ((at ?agent ?node2))
          :del ((at ?agent ?node1))
          :txt (?agent moves from ?node1 to ?node2)
          :cmd {:move ?node2}
          }
    })


(def ops-main2
     '{
       pickup-obj
       {
        :pre ((agent ?agent) (at ?agent ?node1) (at ?object ?node1) (holding ?agent nil) (manipulable ?object))
        :add ((holding ?agent ?object) (at ?object ?agent))
        :del ((holding ?agent nil) (at ?object ?node1))
        :txt (?agent picked-up ?object from ?node1)
        :cmd {:pickup ?object}
        }
       open-door
       {
        :pre ((agent ?agent) (at ?agent ?door) (holding ?agent ?object) (at ?object ?agent) (isa ?object key) (isa ?door door)
               (color ?object ?color) (color ?door ?color) (at ?door ?node1) (connects ?node1 ?door)
               (locked ?door) (manipulable ?object))
        :add ((unlocked ?door) (holding ?agent nil))
        :del ((locked ?door) (manipulable ?object) (isa ?object key) (color ?object ?color) (holding ?agent ?object) (at ?object ?agent)) ;;Consumes key.
        :txt (?agent opens ?door at ?node1 with ?object)
        :cmd {:unlock ?door}
        }
       move-through-door
       {
        :pre ((agent ?agent) (at ?agent ?door) (isa ?door door) (unlocked ?door) (connects ?door ?node2))
        :add ((at ?agent ?node2))
        :del ((at ?agent ?node1))
        :txt (?agent moves through ?door to ?node2)
        :cmd {:move ?node2}
        }
       move
       {
        :pre ((agent ?agent)
               (at ?agent ?node1)
               (connects ?node1 ?node2)
               (isa ?node1 node)  ;;Required to prevent agent walking through doors. This operation is for moving between nodes.
               )
        :add ((at ?agent ?node2))
        :del ((at ?agent ?node1))
        :txt (?agent moves from ?node1 to ?node2)
        :cmd {:move ?node2}
        }
       }
     )

;;Opening a door with a key no longer consumes it. Agents can drop items now.
(def ops-main3
  '{
    drop-obj
    {
     :pre ((agent ?agent) (holding ?agent ?object) (manipulable ?object) (at ?object ?agent) (at ?agent ?node1))
     :add ((holding ?agent nil) (at ?object ?node1))
     :del ((holding ?agent ?object) (at ?object ?agent))
     :txt (?agent dropped ?object at ?node1)
     :cmd {:drop ?object}
     }
    pickup-obj
    {
     :pre ((agent ?agent) (at ?agent ?node1) (at ?object ?node1) (holding ?agent nil) (manipulable ?object))
     :add ((holding ?agent ?object) (at ?object ?agent))
     :del ((holding ?agent nil) (at ?object ?node1))
     :txt (?agent picked-up ?object from ?node1)
     :cmd {:pickup ?object}
     }
    open-door
    {
     :pre ((agent ?agent) (at ?agent ?door) (holding ?agent ?object) (at ?object ?agent) (isa ?object key) (isa ?door door)
            (color ?object ?color) (color ?door ?color) (at ?door ?node1) (connects ?node1 ?door)
            (locked ?door) (manipulable ?object))
     :add ((unlocked ?door) (holding ?agent nil))
     :del ((locked ?door))
     :txt (?agent opens ?door at ?node1 with ?object)
     :cmd {:unlock ?door}
     }
    move-through-door
    {
     :pre ((agent ?agent) (at ?agent ?door) (isa ?door door) (unlocked ?door) (connects ?door ?node2))
     :add ((at ?agent ?node2))
     :del ((at ?agent ?node1))
     :txt (?agent moves through ?door to ?node2)
     :cmd {:move ?node2}
     }
    move
    {
     :pre ((agent ?agent)
            (at ?agent ?node1)
            (connects ?node1 ?node2)
            (isa ?node1 node)  ;;Required to prevent agent walking through doors. This operation is for moving between nodes.
            )
     :add ((at ?agent ?node2))
     :del ((at ?agent ?node1))
     :txt (?agent moves from ?node1 to ?node2)
     :cmd {:move ?node2}
     }
    }
  )