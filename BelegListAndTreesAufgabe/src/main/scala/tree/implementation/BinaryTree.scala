package tree.implementation

import tree.traits.IntTree




import scala.annotation.tailrec









/**
 * A companion object for the BinaryTree.
 * This enables creating trees like: val tree = BinaryTree(1, 2, 3),
 * which results in a BinaryTree with elements inserted in sequence.
 */
object BinaryTree:

  /**
   * The apply function is a special function in Scala that allows for creating a BinaryTree
   * with a sequence of integers. This is a variadic function that accepts any number of integers.
   * 
   * @param xs the sequence of integers to insert into the tree.
   * @return a BinaryTree with the elements of xs inserted in sequence.
   */
  def apply(xs: Int*): BinaryTree =

    // Helper function to insert each element of xs into the tree
    @tailrec
    def treeInitialization(tree: BinaryTree, xs: Int*): BinaryTree = xs match
      case Seq() => tree
      case _ => treeInitialization(tree.insert(xs.head).asInstanceOf[BinaryTree], xs.tail*)

    treeInitialization(Empty, xs*)

/**
 * An abstract class representing a binary tree with integer values.
 * It provides various methods to manipulate the tree, such as insertion, deletion,
 * mapping, and filtering elements.
 */
abstract class BinaryTree extends IntTree {

  /**
   * Finds the successor of the current node in the binary tree.
   * The successor is the smallest node in the right subtree of the current node.
   * 
   * @return the successor node of the current node.
   * @throws Error if there is no successor (e.g., for an empty tree).
   */
  def findSuccessor: BinaryTree = this match {
    case NonEmpty(_, _, right) =>
      right match {
        case Empty => throw new Error("No successor in an empty tree")
        case rightTree: NonEmpty => findLeftmost(rightTree)
      }
    case Empty => throw new Error("No successor in an empty tree")
  }

  /**
   * Finds the leftmost node in a subtree, used to determine the minimum node.
   * 
   * @param node the subtree root node.
   * @return the leftmost node in the subtree.
   */
  @tailrec
  private def findLeftmost(node: NonEmpty): BinaryTree = node.left match {
    case Empty => node
    case leftTree: NonEmpty => findLeftmost(leftTree)
  }

  /**
   * Deletes an element from the binary tree.
   * 
   * @param i the integer value to delete from the tree.
   * @return a new binary tree with the element removed.
   * @throws Error if the element is not found.
   */
  override def delete(i: Int): IntTree = this match {
    case Empty => throw new Error("Node not found for deletion")
    case NonEmpty(value, left, right) =>
      if (i < value) {
        NonEmpty(value, left.delete(i), right)
      } else if (i > value) {
        NonEmpty(value, left, right.delete(i))
      } else {
        (left, right) match {
          case (Empty, _) => right
          case (_, Empty) => left
          case _ =>
            right match {
              case nonEmptyRight: NonEmpty =>
                val successor = findLeftmost(nonEmptyRight)
                NonEmpty(successor.root, left, right.delete(successor.root))
            }
        }
      }
  }

  /**
   * Maps each element in the tree using a provided function.
   * 
   * @param mapFun the function to apply to each element.
   * @return a new binary tree with each element transformed by mapFun.
   */
  override def map(mapFun: Int => Int): IntTree = this match
    case Empty => Empty
    case NonEmpty(value, left, right) =>
      NonEmpty(mapFun(value), left.map(mapFun), right.map(mapFun))

  /**
   * Gathers values that pass a filter function into a list without duplicates.
   * 
   * @param tree the tree to filter.
   * @param filterFun the filtering function.
   * @param seen a set of already-seen values to avoid duplicates.
   * @return a list of values that pass filterFun without duplicates.
   */
  def filterToList(tree: IntTree, filterFun: Int => Boolean, seen: Set[Int]): List[Int] = tree match {
    case Empty => List()
    case NonEmpty(value, left, right) =>
      val currentList = if (filterFun(value) && !seen.contains(value)) List(value) else List()
      val leftList = filterToList(left, filterFun, seen ++ currentList)
      val rightList = filterToList(right, filterFun, seen ++ currentList)
      currentList ++ leftList ++ rightList
  }

  /**
   * Rebuilds a binary tree from a list of integer values.
   * 
   * @param values the list of values to insert into the new tree.
   * @return a new binary tree constructed from the values in the list.
   */
  def rebuildTree(values: List[Int]): IntTree = {
    values.foldLeft(Empty: IntTree)((tree, value) => tree.insert(value))
  }

  /**
   * Filters the binary tree based on a predicate function.
   * 
   * @param filterFun the predicate function to filter tree values.
   * @return a new binary tree containing only elements that pass filterFun.
   */
  override def filter(filterFun: Int => Boolean): IntTree = {
    val filteredValues = filterToList(this, filterFun, Set.empty).distinct
    rebuildTree(filteredValues)
  } 




  
  /**
   * Converts the binary tree to a list in an in-order traversal.
   * 
   * @return a list of elements in the tree in sorted order.
   */
  override def tree2List: List[Int] = this match
    case Empty => List()
    case NonEmpty(value, left, right) =>
      left.tree2List ++ List(value) ++ right.tree2List

  /**
   * Checks if the binary tree maintains the binary search tree (BST) property.
   * 
   * @return true if the tree is a valid BST, false otherwise.
   */
  override def isBinaryTree: Boolean = this match
    case Empty => true
    case NonEmpty(elem, left, right) =>
      val leftCheck = left match {
        case Empty => true
        case NonEmpty(leftElem, _, _) => leftElem < elem && left.isBinaryTree
      }
      val rightCheck = right match {
        case Empty => true
        case NonEmpty(rightElem, _, _) => rightElem > elem && right.isBinaryTree
      }
      leftCheck && rightCheck
}