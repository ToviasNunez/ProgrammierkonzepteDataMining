package tree.implementation

import tree.traits.IntTree

case class NonEmpty(val elem: Int, left: IntTree, right: IntTree) extends BinaryTree :

  override def isEmpty = false

  override def root:Int= elem

  // Recursive contains method to check if the element exists in the tree
  override infix def contains(i: Int): Boolean =
    if (i == elem) true
    else if (i < elem) left.contains(i)
    else right.contains(i)

  override def insert(i:Int):IntTree=
    if (i < elem) NonEmpty(elem, left insert i, right)
    else if (i > elem) NonEmpty(elem, left, right insert i)
    else this

  // Recursive size method to count the number of nodes in the tree
  override def size: Int = 1 + left.size + right.size

  // Recursive height method to find the height of the tree
  override def height: Int = 1 + Math.max(left.height, right.height)
