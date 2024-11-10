package tree.implementation

import tree.traits.IntTree

import scala.annotation.tailrec

/**
 * A companion object for the BinaryTree.
 * This enables creating trees like: val tree = BinaryTree(1,2,3)
 * which results in a BinaryTree with elements inserted in sequence.
 */
object BinaryTree :

  /** The apply function is a special function in Scala.
   * It can be invoked with BinaryTree.apply(args) or simply BinaryTree(args).
   * This implementation is variadic, i.e., it accepts a sequence of integers as parameters.
   */
  def apply(xs: Int*): BinaryTree =

    // Helper function to insert each element of xs into the tree
    @tailrec
    def treeInitialization(tree: BinaryTree, xs: Int*): BinaryTree = xs match
      case Seq() => tree
      // Insert head element into the tree, then recursively insert remaining elements
      case _ => treeInitialization(tree.insert(xs.head).asInstanceOf[BinaryTree], xs.tail*)
    treeInitialization(Empty, xs*)


/**
 * An abstract class representing a binary tree with integer values.
 */
abstract class BinaryTree extends IntTree :

  /* Helper function used by the delete operation
   * Finds the node with the next higher value compared to the root node of
   * the binary tree.
   */
  def findSuccessor: BinaryTree = this match {
    case NonEmpty(_, _, right) =>
      right match {
        case Empty =>
          throw new Error("No successor in an empty tree") // Right subtree is empty
        case rightTree: NonEmpty =>


          // Find the leftmost node in the right subtree
          findLeftmost(rightTree) // Simplified to directly call findLeftmost


      }
    case Empty => throw new Error("No successor in an empty tree") // Current tree is empty
  }

  // Recursive helper function to find the leftmost node
  @tailrec
  private def findLeftmost(node: NonEmpty): BinaryTree = {
    node.left match {
      case Empty => node // If there is no left child, this is the leftmost node
      case leftTree: NonEmpty => findLeftmost(leftTree) // Recurse down to find the leftmost
    }
  }


  override def delete(i: Int): IntTree = this match {
    case Empty => throw new Error("Node not found for deletion") // If the tree is empty, throw an error
    case NonEmpty(value, left, right) =>
      if (i < value) {
        // If the value to delete is less than the current node, go left
        NonEmpty(value, left.delete(i), right)
      } else if (i > value) {
        // If the value to delete is greater than the current node, go right
        NonEmpty(value, left, right.delete(i))
      } else {
        // We found the node to delete
        (left, right) match {
          case (Empty, _) => right // If left is empty, return the right subtree
          case (_, Empty) => left // If right is empty, return the left subtree
          case _ =>
            //val successor = right.findSuccessor // Call findSuccessor directly on right
           // NonEmpty(successor.root, left, right.delete(successor.root)) // Delete successor from right
            // If both children are present, find the successor (minimum in the right subtree)
            right match {
              case nonEmptyRight: NonEmpty =>
               // val successor = nonEmptyRight.findSuccessor // This should work now
                val successor = findLeftmost(nonEmptyRight)
                NonEmpty(successor.root, left, right.delete(successor.root)) // Delete the successor from the right subtree
            }

        }
      }
  }


  override def map(mapFun: Int => Int): IntTree = this match
    case Empty => Empty
    case NonEmpty(value, left, right) =>
      // Create a new NonEmpty node with the mapped value and recursively map the left and right subtrees
      NonEmpty(mapFun(value), left.map(mapFun), right.map(mapFun))
/*
  override def filter(filterFun: Int => Boolean): IntTree = this match {
    case Empty => Empty
    case NonEmpty(value, left, right) =>
      // Recursively filter the left and right subtrees
      val filteredLeft = left.filter(filterFun)
      val filteredRight = right.filter(filterFun)

      // If the current value passes the filter function, include it
      if (filterFun(value)) {
        NonEmpty(value, filteredLeft, filteredRight)
      } else {
        // If the current value doesn't pass, return a new tree that consists only of valid children
        (filteredLeft, filteredRight) match {
          case (Empty, Empty) => Empty // If both subtrees are empty, return Empty
          case (Empty, _) => filteredRight // If left is empty, return the filtered right
          case (_, Empty) => filteredLeft // If right is empty, return the filtered left
          case _ => NonEmpty(filteredLeft.root, filteredLeft, filteredRight) // Return a new NonEmpty with filtered children
        }
      }
  }
*/

  override def filter(filterFun: Int => Boolean): IntTree = this match {
    case Empty => Empty
    case NonEmpty(value, left, right) =>
      // Recursively filter the left and right subtrees
      val filteredLeft = left.filter(filterFun)
      val filteredRight = right.filter(filterFun)

      // Only include `value` if it passes the filter and isn't already in the filtered left or right subtrees
      if (filterFun(value) && !filteredLeft.tree2List.contains(value) && !filteredRight.tree2List.contains(value)) {
        NonEmpty(value, filteredLeft, filteredRight)
      } else {
        // Exclude `value` and combine the filtered children
        (filteredLeft, filteredRight) match {
          case (Empty, Empty) => Empty
          case (Empty, _) => filteredRight
          case (_, Empty) => filteredLeft
          case _ => NonEmpty(filteredLeft.tree2List.head, filteredLeft, filteredRight) // Combining filtered left and right
        }
      }
  }


  override def tree2List: List[Int] = this match
    case Empty => List()
    case NonEmpty(value, left, right) =>
      // Combine the left subtree's list, the current node's value, and the right subtree's list
      left.tree2List ++ List(value) ++ right.tree2List

  override def isBinaryTree: Boolean = this match
    case Empty => true
    case NonEmpty(elem, left, right) =>
      // Ensure all elements in left subtree are less than the current element
      val leftCheck = left match {
        case Empty => true
        case NonEmpty(leftElem, _, _) => leftElem < elem && left.isBinaryTree
      }
      // Ensure all elements in right subtree are greater than the current element
      val rightCheck = right match {
        case Empty => true
        case NonEmpty(rightElem, _, _) => rightElem > elem && right.isBinaryTree
      }
      leftCheck && rightCheck // Both subtrees must also be binary trees



