/*
* Aufgabe 1: Gegeben sei die folgende Liste, die ausdrückt, welche Programmiersprachen,
welche Programmiersprachen welche Paradigmen unterstützen:
val Paradigmen=List(("erlang", "funktional"), ("erlang", "logisch"), ("prolog", "logisch"),
("scala", "funktional"), ("scala", "objektorientiert"), ("scala", "logisch"),
("java","objektorientiert"))
Erstellen Sie die folgenden Listen über die for-Schleife sowie über die Funktionen map,
flatMap und filter:
a) Eine Liste aller Programmiersprachen, die objektorientiert sind.
b) Eine Liste aller Paradigmen der Sprachen erlang und java.
c) Eine Liste aller Programmiersprachen, die mehr als ein Paradigma beinhaltet.
d) Eine Liste von Tupeln, die als erstes Element die Programmiersprache enthält und als
zweites Element eine Liste der Paradigmen.
Bei allen Ergebnissen können Duplikate vorkommen. Sie müssen nicht gefiltert werden.
* */

val Paradigmen = List(("erlang", "funktional"), ("erlang", "logisch"), ("prolog", "logisch"),
  ("scala", "funktional"), ("scala", "objektorientiert"), ("scala", "logisch"),
  ("java","objektorientiert"))

//a) Eine Liste aller Programmiersprachen, die objektorientiert sind.

Paradigmen.filter(_._2 == "objektorientiert").map(_._1)

//b) Eine Liste aller Paradigmen der Sprachen erlang und java.
Paradigmen.filter( p => p._1 == "erlang" || p._1 == "java").map(_._2)

//c) Eine Liste aller Programmiersprachen, die mehr als ein Paradigma beinhaltet.
Paradigmen.groupBy(_._1).filter(_._2.size >1).keys.toList

// d) Eine Liste von Tupeln, die als erstes Element die Programmiersprache enthält und als
//zweites Element eine Liste der Paradigmen.

Paradigmen.groupBy(_._1).map{case (k,v) => (k,v.map(_._2))}.toList