import scala.collection.mutable.ArrayBuffer

@main
def List_Array(): Unit = {
 val l = List(1,2,3)
 println(l)

 val m = List(1,true ,"hello")

 println(m)
 val n = List()
 val o = Nil

 println(n)
 println(o)

 //val p =  List[Int](1,22,3,3 , "Hello")
 //println(p)

 val matrix = List(
    List(1,0,0)
    ,List(0,1,0),
    List(0,0,1)
 )

 println(matrix)
  println(matrix(0)(1))
  println(l(2))


  // head and tail --> head first element and tail is the rest

  val listA = List("red","green", "red")

  println(listA.head)
  println(listA.tail)
  println(listA.tail.head)


  // array , element  can be modify mutable but the size can be change

  val a = Array(1,2,3,2,5)
  val a1 = Array(1,2,3,2,true)


  println(a)
  a.foreach(println)

  println(a.mkString)
  println(a.toList)
  a(1) = 4
   println(a.toList)


   // add element to the end of the array creating a new array 
   val b = 0+: a:+4
   println(b.toList)

   // concateneted a array 

   val c = a ++ b
   println(c.toList)


   // arraybuffer 

   val d = ArrayBuffer(1,2,3)
}