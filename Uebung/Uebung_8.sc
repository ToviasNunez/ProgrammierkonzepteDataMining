val Paradigmen=List(("erlang", "funktional"), ("erlang", "logisch"), ("prolog", "logisch"),
  ("scala", "funktional"), ("scala", "objektorientiert"), ("scala", "logisch"),
  ("java","objektorientiert"))


// a) Eine Liste aller Programmiersprachen, die objektorientiert sind

Paradigmen.filter{case(_,paradigma) => paradigma == "objektorientiert"}.map{case (sprache,_) => sprache}

//b) Eine Liste aller Paradigmen der Sprachen erlang und java.
Paradigmen.filter(p => p._1 == "erlang" || p._1 =="java").map(_._2)

//c) Eine Liste aller Programmiersprachen, die mehr als ein Paradigma beinhaltet.
Paradigmen.groupBy(_._1).filter(_._2.size > 1).keys

//d) Eine Liste von Tupeln, die als erstes Element die Programmiersprache enthält und als zweites Element eine Liste der Paradigmen.

Paradigmen.groupBy(_._1)// first elem program name
  .map{case(sprache,paradigmen) // tupeln (program name: paradigmen)
  => (sprache,paradigmen.map(_._2).distinct)} // tupeln (name , list from the paradigmen )