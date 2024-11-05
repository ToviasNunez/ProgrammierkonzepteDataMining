package list.implementation

import list.traits.IntList

/**
  * A companion object for the singly linked list.
  * This enables creating lists list this: val list = SinglyLinkedIntList(1,2,3)
  * which results in Cons(1,Cons(2,Cons(3,Empty))))
  */
object SinglyLinkedIntList :

  /** The apply function is a special function in scala.
    * It can be invoked with SinglyLinkedIntList.apply(args) or simply SinglyLinkedIntList(args).
    * This particular implementation of it is also a variadic function, i.e.
    * a function which accepts one or more arguments of the same type (integers) as parameters.
    */
  //inside this method xs is of type Seq[int]
  def apply(xs: Int*): SinglyLinkedIntList = xs match
    case Seq() => Empty
    //: _* results in the sequence being passed as multiple parameters - (1,2,3) instead of Seq[Int]{1,2,3}
    case _ => Cons(xs.head, SinglyLinkedIntList(xs.tail*))


abstract class SinglyLinkedIntList extends IntList :

  override def prefix(other: IntList): IntList = other match
    case Empty => this
    case Cons(h,t) => Cons(h,prefix(t))

  // Returns the number of elements in the list
  override def size: Int = this match
    case Empty => 0
    case Cons(_,t) => 1 + t.size
  // Transforms each element using `mapFunc`
  override def map(mapFunc: Int => Int): IntList = this match
    case Empty => Empty
    case Cons(h , t) => Cons(mapFunc(h) , t.map(mapFunc))

  // Returns a new list with elements that satisfy `filterFunc`
  override def filter(filterFunc: Int => Boolean): IntList = this match
    case Empty => Empty
    case Cons(h , t) if filterFunc(h) => Cons(h, t.filter(filterFunc))
    case Cons(_,t) => t.filter(filterFunc)

  // Reduces the list from the right using `reduceFunc`
  override def foldRight(initial: Int)(reduceFunc: (Int, Int) => Int): Int = this match
    case Empty => initial
    case Cons(h,t) => reduceFunc(h, t.foldRight(initial)(reduceFunc))

  /* Do not use a fold-operator for the implementation!*/
  // Reduces the list from the right without using `fold`
  override def reduceRight(reduceFunc: (Int, Int) => Int): Int = this match
    case Cons(h,Empty) => h
    case Cons(h,t) => reduceFunc(h , t.reduceRight(reduceFunc))
    case Empty => throw  new UnsupportedOperationException("reduceRight on empty list")

  // Checks if all elements satisfy `predicateFunc`
  override def forAll(predicateFunc: Int => Boolean): Boolean = this match
    case Empty => true
    case Cons(h,t) => predicateFunc(h) && t.forAll(predicateFunc)

  // Reduces the list from the left using `reduceFunc`
  override def foldLeft(initial: Int)(reduceFunc: (Int, Int) => Int): Int = this match
    case Empty => initial
    case Cons(h , t) => t.foldLeft(reduceFunc(initial, h))(reduceFunc)

  /* Do not use a fold-operator for the implementation!*/
  // Reduces the list from the left without using `fold`
  override def reduceLeft(reduceFunc: (Int, Int) => Int): Int = this match
    case Cons(h, Empty) => h
    case Cons(h , t) => t.reduceLeft((acc,elem) => reduceFunc(acc,elem))
    case Empty => throw new UnsupportedOperationException("reduceLeft on empty list")

  // Inserts `elem` into the sorted list
  override def insertSorted(elem: Int): IntList = this match
    case Empty => Cons(elem, Empty)
    case Cons(h , t) if elem <= h => Cons(elem,this)
    case Cons(h , t) => Cons(h , t.insertSorted(elem))

  // Returns a sorted list using insertion sort
  override def insertionSort: IntList = this match
    case Empty => Empty
    case Cons(h,t) => t.insertionSort.insertSorted(h)
  // Reverses the list
  override def flip: IntList = {
    def reverse(acc: IntList, list: IntList): IntList = list match
      case Empty => acc
      case Cons(h ,t) => reverse(Cons(h, acc), t)

    reverse(Empty, this)
  }