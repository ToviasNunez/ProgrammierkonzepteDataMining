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
   * @param xs The sequence of integers to insert into the tree.
   * @return A BinaryTree with the elements of xs inserted in sequence.
   */
  def apply(xs: Int*): BinaryTree =

    /**
     * Helper function to insert each element of xs into the tree.
     * @param tree The current state of the tree.
     * @param xs The remaining elements to insert.
     * @return A BinaryTree with all elements of xs inserted.
     */
    @tailrec
    def treeInitialization(tree: BinaryTree, xs: Int*): BinaryTree = xs match
      case Seq() => tree // If no elements remain, return the current tree.
      case _ =>
        // Insert the first element of xs and recurse for the rest.
        treeInitialization(tree.insert(xs.head).asInstanceOf[BinaryTree], xs.tail*)

    // Start with an empty tree and initialize it with all elements in xs.
    treeInitialization(Empty, xs*)

/**
 * An abstract class representing a binary tree with integer values.
 * It provides various methods to manipulate the tree, such as insertion, deletion,
 * mapping, filtering, and converting it to a list.
 */
abstract class BinaryTree extends IntTree:

  /**
   * Finds the successor of the current node in the binary tree.
   * The successor is the smallest node in the right subtree of the current node.
   *
   * @return The successor node of the current node.
   * @throws Error if there is no successor (e.g., for an empty tree).
   */
  def findSuccessor: BinaryTree = this match
    case NonEmpty(_, _, right) =>
      right match {
        case Empty => throw new Error("No successor in an empty tree")
        case rightTree: NonEmpty => findLeftmost(rightTree) // Find the smallest element in the right subtree.
      }
    case Empty => throw new Error("No successor in an empty tree")

  /**
   * Finds the leftmost node in a subtree, used to determine the minimum node.
   *
   * @param node The subtree root node.
   * @return The leftmost node in the subtree.
   */
  @tailrec
  private def findLeftmost(node: NonEmpty): BinaryTree = node.left match
    case Empty => node // If no left child exists, the current node is the leftmost.
    case leftTree: NonEmpty => findLeftmost(leftTree) // Recurse further down the left subtree.

  /**
   * Inserts a new integer value into the binary tree.
   *
   * @param value The value to insert.
   * @return A new binary tree with the value inserted in the correct position.
   */
  override def insert(value: Int): IntTree = this match
    case Empty => NonEmpty(value, Empty, Empty) // Insert into an empty tree.
    case NonEmpty(root, left, right) =>
      if (value <= root) NonEmpty(root, left.insert(value), right) // Insert into the left subtree if smaller or equal.
      else NonEmpty(root, left, right.insert(value)) // Insert into the right subtree if greater.

  /**
   * Deletes an element from the binary tree.
   *
   * @param i The integer value to delete from the tree.
   * @return A new binary tree with the element removed.
   * @throws Error if the element is not found.
   */
  override def delete(i: Int): IntTree = this match
    case Empty => throw new Error("Node not found for deletion")
    case NonEmpty(value, left, right) =>
      if (i < value) {
        // Recurse into the left subtree if the value to delete is smaller.
        NonEmpty(value, left.delete(i), right)
      } else if (i > value) {
        // Recurse into the right subtree if the value to delete is greater.
        NonEmpty(value, left, right.delete(i))
      } else {
        // Case where the current node matches the value to delete.
        (left, right) match {
          case (Empty, _) => right // If the left subtree is empty, return the right subtree.
          case (_, Empty) => left // If the right subtree is empty, return the left subtree.
          case _ =>
            // Both subtrees are non-empty. Replace the current node with its successor.
            right match {
              case nonEmptyRight: NonEmpty =>
                val successor = findLeftmost(nonEmptyRight)
                NonEmpty(successor.root, left, right.delete(successor.root))
            }
        }
      }

  /**
   * Maps each element in the tree using a provided function.
   *
   * @param mapFun The function to apply to each element.
   * @return A new binary tree with each element transformed by mapFun.
   */
  override def map(mapFun: Int => Int): IntTree = this match
    case Empty => Empty // An empty tree remains empty after mapping.
    case NonEmpty(value, left, right) =>
      // Apply the function to the current node's value and recurse into its children.
      NonEmpty(mapFun(value), left.map(mapFun), right.map(mapFun))

  /**
   * Gathers values that pass a filter function into a list without duplicates.
   *
   * @param tree The tree to filter.
   * @param filterFun The filtering function.
   * @param seen A set of already-seen values to avoid duplicates.
   * @return A list of values that pass filterFun without duplicates.
   */
  def filterToList(tree: IntTree, filterFun: Int => Boolean, seen: Set[Int]): List[Int] = tree match
    case Empty => List() // An empty tree contributes no elements.
    case NonEmpty(value, left, right) =>
      val currentList = if (filterFun(value) && !seen.contains(value)) List(value) else List()
      val leftList = filterToList(left, filterFun, seen ++ currentList)
      val rightList = filterToList(right, filterFun, seen ++ currentList)
      currentList ++ leftList ++ rightList

  /**
   * Rebuilds a binary tree from a list of integer values.
   *
   * @param values The list of values to insert into the new tree.
   * @return A new binary tree constructed from the values in the list.
   */
  def rebuildTree(values: List[Int]): IntTree =
    values.foldLeft(Empty: IntTree)((tree, value) => tree.insert(value))

  /**
   * Filters the binary tree based on a predicate function.
   *
   * @param filterFun The predicate function to filter tree values.
   * @return A new binary tree containing only elements that pass filterFun.
   */
 /* override def filter(filterFun: Int => Boolean): IntTree =
    rebuildTree(filterToList(this, filterFun, Set.empty).distinct)*/
  override def filter(filterFun: Int => Boolean): IntTree = this match
    case Empty => Empty
    case NonEmpty(elem, left , right) =>
      if filterFun(elem) then NonEmpty(elem, left.filter(filterFun) , right.filter(filterFun))
      else delete(elem).filter(filterFun)

  /**
   * Converts the binary tree to a list in an in-order traversal.
   *
   * @return A list of elements in the tree in sorted order.
   */
  override def tree2List: List[Int] = this match
    case Empty => List() // An empty tree produces an empty list.
    case NonEmpty(value, left, right) =>
      left.tree2List ++ List(value) ++ right.tree2List

  /**
   * Checks if the binary tree maintains the binary search tree (BST) property.
   *
   * @return True if the tree is a valid BST, false otherwise.
   */
  override def isBinaryTree: Boolean = this match
    case Empty => true // An empty tree satisfies the BST property.
    case NonEmpty(elem, left, right) =>
      val leftCheck = left match
        case Empty => true
        case NonEmpty(leftElem, _, _) => leftElem < elem && left.isBinaryTree
      val rightCheck = right match
        case Empty => true
        case NonEmpty(rightElem, _, _) => rightElem > elem && right.isBinaryTree
      leftCheck && rightCheck
