package list.implementation

import list.traits.IntList
import scala.annotation.tailrec

/**
 * A companion object for the singly linked list.
 * This enables creating lists like this: val list = SinglyLinkedIntList(1, 2, 3)
 * which results in Cons(1, Cons(2, Cons(3, Empty)))
 */
object SinglyLinkedIntList :

  /**
   * The `apply` function is a special function in Scala.
   * It can be invoked as SinglyLinkedIntList.apply(args) or simply SinglyLinkedIntList(args).
   * This particular implementation is also a variadic function, i.e.,
   * a function that accepts zero or more arguments of the same type (integers) as parameters.
   */
  def apply(xs: Int*): SinglyLinkedIntList = xs match
    case Seq() => Empty
    // `: _*` results in the sequence being passed as multiple arguments
    // For example, Seq(1, 2, 3) becomes (1, 2, 3)
    case _ => Cons(xs.head, SinglyLinkedIntList(xs.tail*))

/**
 * Abstract class for a singly linked list, extending the `IntList` trait.
 * Implements common operations for functional-style list manipulation.
 */
abstract class SinglyLinkedIntList extends IntList :

  /**
   * Appends another list (`other`) to the front of the current list.
   * @param other Another list to be prefixed.
   * @return A new list with `other` prefixed.
   */
  override def prefix(other: IntList): IntList = other match
    case Empty => this // If the other list is empty, return the current list.
    case Cons(h, t) => Cons(h, prefix(t)) // Recurse through the elements of `other`.

  /**
   * Calculates the total number of elements in the list.
   * @return The size of the list.
   */
  override def size: Int = this match
    case Empty => 0 // An empty list has size 0.
    case Cons(_, t) => 1 + t.size // Add 1 for the head, then calculate size recursively for the tail.

  /**
   * Applies a transformation function to each element of the list.
   * @param mapFunc A function to transform each element.
   * @return A new list with transformed elements.
   */
  override def map(mapFunc: Int => Int): IntList = this match
    case Empty => Empty // An empty list remains empty after mapping.
    case Cons(h, t) => Cons(mapFunc(h), t.map(mapFunc)) // Transform the head, and map the tail recursively.

  /**
   * Filters the elements of the list based on a predicate function.
   * @param filterFunc A function that returns true for elements to keep.
   * @return A new list with only elements satisfying the predicate.
   */
  override def filter(filterFunc: Int => Boolean): IntList = this match
    case Empty => Empty // An empty list remains empty after filtering.
    case Cons(h, t) if filterFunc(h) => Cons(h, t.filter(filterFunc)) // Keep the head if it satisfies the predicate.
    case Cons(_, t) => t.filter(filterFunc) // Skip the head if it doesn't satisfy the predicate.

  /**
   * Folds the list from the right with an initial value and a combining function.
   * @param initial The initial value for the fold operation.
   * @param reduceFunc The combining function.
   * @return The result of folding from the right.
   */
  override def foldRight(initial: Int)(reduceFunc: (Int, Int) => Int): Int = this match
    case Empty => initial // Return the initial value when the list is empty.
    case Cons(h, t) => reduceFunc(h, t.foldRight(initial)(reduceFunc)) // Combine the head with the fold result of the tail.

  /**
   * Reduces the list from the right without using fold.
   * @param reduceFunc The combining function.
   * @return The reduced result.
   * @throws UnsupportedOperationException if the list is empty.
   */
  override def reduceRight(reduceFunc: (Int, Int) => Int): Int = this match
    case Cons(h, Empty) => h // A single-element list reduces to its only element.
    case Cons(h, t) => reduceFunc(h, t.reduceRight(reduceFunc)) // Combine the head with the reduction of the tail.
    case Empty => throw new UnsupportedOperationException("reduceRight on empty list")

  /**
   * Checks if all elements satisfy a predicate.
   * @param predicateFunc The predicate function.
   * @return True if all elements satisfy the predicate, false otherwise.
   */
  override def forAll(predicateFunc: Int => Boolean): Boolean = this match
    case Empty => true // An empty list satisfies any predicate.
    case Cons(h, t) => predicateFunc(h) && t.forAll(predicateFunc) // Check the head and recurse through the tail.

  /**
   * Folds the list from the left with an initial value and a combining function.
   * @param initial The initial value for the fold operation.
   * @param reduceFunc The combining function.
   * @return The result of folding from the left.
   */
  override def foldLeft(initial: Int)(reduceFunc: (Int, Int) => Int): Int = this match
    case Empty => initial // Return the initial value when the list is empty.
    case Cons(h, t) => t.foldLeft(reduceFunc(initial, h))(reduceFunc) // Combine the initial value with the head, then fold the tail.

  /**
   * Reduces the list from the left without using fold.
   * @param reduceFunc The combining function.
   * @return The reduced result.
   * @throws UnsupportedOperationException if the list is empty.
   */
  override def reduceLeft(reduceFunc: (Int, Int) => Int): Int = this match {
    case Empty => throw new UnsupportedOperationException("reduceLeft on empty list")
    case Cons(h, t) =>
      @tailrec
      def loop(acc: Int, remaining: IntList): Int = remaining match {
        case Empty => acc // Return the accumulated value when the list is empty.
        case Cons(head, tail) => loop(reduceFunc(acc, head), tail) // Update the accumulator and recurse.
      }
      loop(h, t) // Start the loop with the head as the initial accumulator and the tail as the remaining list.
  }

  /**
   * Inserts an element into the sorted list, preserving order.
   * @param elem The element to insert.
   * @return A new list with the element inserted.
   */
  override def insertSorted(elem: Int): IntList = this match
    case Empty => Cons(elem, Empty) // Insert into an empty list.
    case Cons(h, t) if elem <= h => Cons(elem, this) // Insert before the current head if the element is smaller or equal.
    case Cons(h, t) => Cons(h, t.insertSorted(elem)) // Recurse into the tail to find the correct position.

  /**
   * Sorts the list using insertion sort.
   * @return A sorted list.
   */
  override def insertionSort: IntList = this match
    case Empty => Empty // An empty list is already sorted.
    case Cons(h, t) => t.insertionSort.insertSorted(h) // Sort the tail first, then insert the head in the sorted list.

  /**
   * Reverses the list.
   * @return A new list with elements in reverse order.
   */
  override def flip: IntList = this match
    case Empty => Empty // An empty list remains empty after reversal.
    case Cons(h, t) => Cons(h, Empty).prefix(t.flip) // Recurse through the tail, reversing each element.
