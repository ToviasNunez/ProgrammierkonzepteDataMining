class A{def aMethod(x:Int): Int = x+1}
class B extends A{
  override def aMethod(x: Int): Int = x+2}

val a = new A
val b = new B
a.aMethod(3)
b.aMethod(3)

val c = a.asInstanceOf[B].aMethod(3)

val d = b.asInstanceOf[A].aMethod(3)


// deleted element from the list
