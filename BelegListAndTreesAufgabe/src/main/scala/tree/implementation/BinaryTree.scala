package tree.implementation

import tree.traits.IntTree

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
          // Now find the leftmost node in the right subtree
         /* rightTree.left match {
            case Empty => rightTree // If there's no left child, the right node is the successor
            case leftTree: NonEmpty => findLeftmost(leftTree) // Recurse to find the leftmost
            // case leftTree: NonEmpty => leftTree.findLeftmost // Recurse to find the leftmost
          }*/

          // Find the leftmost node in the right subtree
          findLeftmost(rightTree) // Simplified to directly call findLeftmost
      }
    case Empty =>
      throw new Error("No successor in an empty tree") // Current tree is empty
  }

  // Recursive helper function to find the leftmost node
  private def findLeftmost(node: NonEmpty): BinaryTree = {
    node.left match {
      case Empty => node // If there is no left child, this is the leftmost node
      case leftTree: NonEmpty => findLeftmost(leftTree) // Recurse down to find the leftmost
    }
  }

  override def delete(i: Int): IntTree = this match {
    case Empty => Empty
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
                val successor = nonEmptyRight.findSuccessor // This should work now
                NonEmpty(successor.root, left, right.delete(successor.root)) // Delete the successor from the right subtree
            }
        }
      }
  }


  override def map(mapFun: Int => Int): IntTree = this match
    case Empty => Empty
    case nonEmpty: NonEmpty => nonEmpty.map(mapFun)

  override def filter(filterFun: Int => Boolean): IntTree = this match
    case Empty => Empty
    case nonEmpty: NonEmpty => nonEmpty.filter(filterFun)

  override def tree2List: List[Int] = this match
    case Empty => List()
    case nonEmpty: NonEmpty => nonEmpty.tree2List

  override def isBinaryTree: Boolean = this match
    case Empty => true
    case nonEmpty: NonEmpty => nonEmpty.isBinaryTree


