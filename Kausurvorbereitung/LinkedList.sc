
abstract class IntList{
  def isEmpty:Boolean
  def head:Int
  def tail:IntList
  def prefix(elem:IntList):IntList
  def flip:IntList
  def changeNumber(pred:Int=>Boolean, change: Int=>IntList):IntList

  def map(f: Int => Int): IntList

  def filter(p: Int => Boolean): IntList

  def flatMap(f: Int => IntList): IntList

  def foldLeft[B](z: B)(op: (B, Int) => B): B

  def reduce(op: (Int, Int) => Int): Int

  def reverse: IntList

  def append(list: IntList): IntList

  def contains(x: Int): Boolean

  def length: Int

  def take(n: Int): IntList

  def drop(n: Int): IntList

}

case object Empty extends IntList{
  def isEmpty = true
  def head= throw new Error ("List is Empty")
  def tail= throw new Error ("List is Empty")
  def prefix(elem:IntList):IntList= Empty
  def flip:IntList= Empty
  def changeNumber(pred:Int=>Boolean, change: Int=>IntList):IntList= Empty

  override def map(f: Int => Int): IntList = this

  override def filter(p: Int => Boolean): IntList = this

  override def flatMap(f: Int => IntList): IntList =  this

  override def foldLeft[B](z: B)(op: (B, Int) => B): B = z

  override def reduce(op: (Int, Int) => Int): Int = throw new UnsupportedOperationException("Empty list cannot be reduced")

  override def reverse: IntList = this

  override def append(list: IntList): IntList = list

  override def contains(x: Int): Boolean = false

  override def length: Int = 0

  override def take(n: Int): IntList = this

  override def drop(n: Int): IntList = this
}

case class Cons(head: Int, tail: IntList) extends IntList {

   def isEmpty: Boolean = true

   def prefix(elem: IntList): IntList = elem match
     case Empty => this
     case Cons(h, t) => Cons(h,prefix(t))

   def flip: IntList = this match
     case Cons(h,t) => Cons(h,Empty).prefix(t.flip)

   def changeNumber(pred: Int => Boolean, change: Int => IntList): IntList = this match
     case Cons(head,tail) if pred(head) => tail.changeNumber(pred,change).prefix(change(head))
     case Cons(head,tail) => Cons(head, tail.changeNumber(pred, change))

  override def map(f: Int => Int): IntList = Cons(f(head),tail.map(f))

  override def filter(p: Int => Boolean): IntList =
    if (p(head)) Cons(head, tail.filter(p)) else tail.filter(p)

  override def flatMap(f: Int => IntList): IntList = f(head).append(tail.flatMap(f))

  override def foldLeft[B](z: B)(op: (B, Int) => B): B = tail.foldLeft(op(z, head))(op)

  override def reduce(op: (Int, Int) => Int): Int = tail match
    case Empty => head
    case Cons(_ ,_) => op(head, tail.reduce(op))

  override def reverse: IntList = tail.reverse.append(Cons(head,Empty))

  override def append(list: IntList): IntList = Cons(head, tail.append(list))

  override def contains(x: Int): Boolean = head == x || tail.contains(x)

  override def length: Int = 1 + tail.length

  override def take(n: Int): IntList = if(n <= 0) Empty else Cons(head, tail.take(n-1))

  override def drop(n: Int): IntList = if(n>0) tail.drop(n-1) else this
}
var list = Cons(1, Cons(2, Cons(3, Empty)))
// 1. is Empty , check if the list os empty EXAMPLE list.isEmpty will return TRUE if the list is empty and  false otherwise
list.isEmpty

// 2. head: return the first element of this list, EXAMPLE list.head will give the first element

list.head
// 3 tail return the remainder of the list excluding the first element
list.tail
// 4. prefix  add element (or another list ) to the front of the list
val list_1 = list.prefix(Cons(4,Empty))
// 5. flip : Reverse the order of element in the list
list_1.flip
// 6. changeNumber : Modifies the element of the list hased on a predicate. if the predicate is tru, then the element is transformed
list_1.changeNumber(_ % 2 == 0, element => Cons(element, Cons(element,Empty)))


// Operational in the list

// 7.  map : applies a function to each element of the list, returning a new list with the transformation elements
list_1.map(x => x * 2)

// 8. filter: func applies a predicate to each elem of the list and includes those elem that satisfy the predicate in the new list
list_1.filter(_ > 2)

// 9.flatMap . the func is similar to map , but each elem is transformed into a collection, the result is a flattened list of collection
list_1.flatMap(x => Cons(x, Cons(x+1, Empty)))

// 10. foldLeft: func recursively combines the elem of the list a give func starting with an initial value
list_1.foldLeft(0)(_ + _)

// 11. reduce: similar to foldLeft, but is doesn't require an initial value, it reduces the list to single value by applying a func
list_1.reduce(_+_)

// 12. reverse: func reverse the order of the elem in this list
list_1.reverse

//13. append : the func concatenates two linked lists
val list4 = Cons(5, Cons(6, Empty))
val list_5 = list_1.append(list4)

//14 contains: func checks if a specific elem exists in the list
list_1.contains(2)
// 15. length : func computes the length (number od elem) in the lis
list_5.length


//16. take: func takes the firs n elem from the list
list_5.take(2)

//17. drop

