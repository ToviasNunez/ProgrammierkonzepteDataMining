package recursions

import list.traits.IntList
import list.implementation._

object ProblemsLists :

  /*
   * Use only recursions for your solutions
   */

  /**
   * Given a number `i` that should be duplicated a certain number of times,
   * returns an IntList that contains the duplicated `i`.
   *
   * E.g., duplicateNum(4,3) -> SinglyLinkedList(4, 4, 4)
   *
   * @param i number to duplicate
   * @param times number of duplicates
   * @return IntList of duplicated numbers
   */
  def duplicateNum(i: Int, times: Int): IntList = times match
    case 0 => Empty // Base case: no duplicates needed, return empty list
    case _ =>
      // Recursive case: add `i` to the list and call duplicateNum with times - 1
      Cons(i, duplicateNum(i, times - 1))

  /**
   * Given an IntList `l` containing arbitrary numbers and a predicate function Int => Boolean,
   * duplicates all numbers in the list that fulfill the predicate.
   * Returns an IntList that contains all duplicated numbers that fulfill the predicate and the
   * remaining other numbers in the same order as they occur in the original list.
   *
   * E.g., duplicateNumbersFulfillingPredicate(x => x % 2 == 0, SinglyLinkedIntList(1,4,3,5,8))
   * -> SinglyLinkedIntList(1, 4, 4, 3, 5, 8, 8)
   *
   * Use only recursion to solve the problem and none of the higher-order functions.
   *
   * @param predicate predicate function to determine if a number should be duplicated
   * @param l IntList to process
   * @return IntList with duplicates and other elements in original order
   */
  def duplicateNumbersFulfillingPredicate(predicate: Int => Boolean, l: IntList): IntList = l match
    case Empty => Empty // Base case: empty list has no elements to process
    case Cons(head, tail) =>
      // Recursive case: check if `head` fulfills the predicate
      if (predicate(head))
        // If predicate is true, duplicate `head` by adding it twice
        Cons(head, Cons(head, duplicateNumbersFulfillingPredicate(predicate, tail)))
      else
        // If predicate is false, add `head` once and process the tail
        Cons(head, duplicateNumbersFulfillingPredicate(predicate, tail))

  /**
   * Given an IntList `l` containing arbitrary numbers, builds all subsets of the list `l`.
   * Returns a List of IntList that contains all subsets.
   *
   * E.g., combinations(SinglyLinkedList(1,2,3)) -> Set(Cons(1, Cons(2, Cons(3, Empty))), Cons(1, Cons(2, Empty)),
   * Cons(1, Cons(3, Empty)), Cons(1, Empty), Cons(2, Cons(3, Empty)), Cons(2, Empty), Cons(3, Empty), Empty)
   *
   * @param l IntList to generate subsets for
   * @return List of all subsets as IntLists
   */
  def combinations(l: IntList): List[IntList] = l match
    case Empty => List(Empty) // Base case: the only subset of an empty list is itself
    case Cons(head, tail) =>
      // Recursive step: get all combinations of the tail
      val tailCombinations = combinations(tail)
      // Combine each subset of tailCombinations with and without `head`
      tailCombinations ++ tailCombinations.map(subset => Cons(head, subset))
