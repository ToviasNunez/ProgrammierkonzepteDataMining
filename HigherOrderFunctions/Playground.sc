val list = List(1, 2 ,3 , 4,5,6,7,8)


for(x<- list) yield x*x
list.map(x=>x*x)

for{x <- list if((x*x)%2==0)} yield x*x
list.map(x=>x*x).filter(x=>x%2==0)

for(i<- 1 to 3 ; j<- List('a','b', 'c')) yield (i,j)

val list1 = List(1 ,2 ,3)

list1.map(x=> List('a','b', 'c') map(y => (x,y)))

/*
* Create two functions that determines which films have been seen by „francesco“.
Use a for-loop for the first function and the operations map, flatMap und filter for
      the second.
* */

val db =List(("francesco", "bloodsports"), ("simon", "jamesBond"), ("marcus",
  "jamesBond"), ("francesco", "die12KammernDerShaolin"))


for (x<- db if(x._1=="francesco")) yield x._2

db.filter(x=> x._1=="francesco").map(_._2)

/*
* Create two functions that determine who have seen the film „jamesBond“. Use a
for-loop for the first function and the operations map, flatMap und filter for the
second.*/

for (x<- db if(x._2=="jamesBond")) yield x._1
db.filter(x=> x._2=="jamesBond").map(_._1)


/*
*Create two functions that determine who have seen more than two films. Use a
for-loop for the first function and the operations map, flatMap und filter for the
second.
* */

(for(x <- db ; y <- db if(x._1 == y._1 && x._2 != y._2)) yield x._1).distinct

db.flatMap(x => db.map(y=>(x,y))).filter(z => z._1._1 == z._2._1 && z._1._2 != z._2._2).map(_._1._1)


//Alternative better
db.flatMap(x => db.map( y => (x,y))).filter{case (x,y) => x._1 == y._1 && x._2 != y._2}.map(_._1._1).distinct


/*
* Create two functions that determine who have seen what films. The result should
contain a list of tuples which contain the name of each person as the fist element
and a list of all films the person has seen as the second. Use a for-loop for the
first function and the operations map, flatMap und filter for the second.
* */
(for(x<- db) yield (x._1, for(y<- db if(y._1==x._1)) yield y._2)).distinct
db.map(x=>(x._1, db.filter(y => x._1 == y._1).map(_._2))).distinct

//Alternative better
for (name <- db.map(_._1).distinct) yield (name, db.filter(_._1 == name).map(_._2))
db.groupBy(_._1).view.mapValues(_.map(_._2)).toList

/*+++++++++++MAPREDUCE+++++++++++++*/
