(import org.nlogo.app.App)
(require '[matcher :refer :all])

(def nlm1 (App/main (into-array String [])))

(defn sendToNL [string]
  (.command (App/app) string))

(defn newPoint [node]
  (.command (App/app) (str "set aim-node \"" node "\"" ))
  (.command (App/app) "go-to-node"))

(defn unlockDoor [door]
  (.command (App/app) (str "set aim-node \"" door "\"" ))
  (.command (App/app) "open-door"))

(defn pickupItem [obj]
  (.command (App/app) (str "set aim-node \"" obj "\"" ))
  (.command (App/app) "pick-up-obj"))

(defn dropItem []
  (.command (App/app) "drop-obj"))

(defn startNetlogo []
  (.open (.workspace (App/app)) "Model.nlogo")
  (.command (App/app) "create-robots 1 [setxy 0 0]"))

(defn setupNetlogoExample2 [node]
  (.open (.workspace (App/app)) "Model.nlogo")
  (.command (App/app) "setup-example-two")
  (.command (App/app) (str "setRobotFromStartNode \""node"\"")))

(defn setupNetlogoExample2-doors [node]
  (.open (.workspace (App/app)) "Model.nlogo")
  (.command (App/app) "setup-example-two-doors")
  (.command (App/app) (str "setRobotFromStartNode \""node"\"")))

(defn setupNetlogoExample3 [node]
  (.open (.workspace (App/app)) "Model.nlogo")
  (.command (App/app) "setup-example-three")
  (.command (App/app) (str "setRobotFromStartNode \""node"\"")))

(defn setupNetlogoExample3-doors [node]
  (.open (.workspace (App/app)) "Model.nlogo")
  (.command (App/app) "setup-example-three-doors")
  (.command (App/app) (str "setRobotFromStartNode \""node"\"")))

(startNetlogo)
