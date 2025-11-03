classDiagram
direction BT
class Arc
class Dijkstra
class GraphImporter
class Graphe
class GrapheHHAdj
class GrapheLAdj
class GrapheLArcs
class GrapheMAdj
class IGraphe {
<<Interface>>

}
class IGraphe {
<<Interface>>

}
class IGrapheConst {
<<Interface>>

}
class IGrapheTest

GraphImporter  ..>  Arc : «create»
Graphe  ..>  IGraphe 
GrapheHHAdj  ..>  IGraphe 
GrapheLAdj  -->  Graphe 
GrapheLArcs "1" *--> "arcs *" Arc 
GrapheLArcs  ..>  Arc : «create»
GrapheLArcs  -->  Graphe 
GrapheMAdj  -->  Graphe 
IGraphe  -->  IGrapheConst 
IGraphe  -->  IGrapheConst 
IGrapheTest  ..>  GrapheLAdj : «create»
IGrapheTest  ..>  GrapheLArcs : «create»
IGrapheTest  ..>  GrapheMAdj : «create»
IGrapheTest "1" *--> "graphes *" IGraphe 
