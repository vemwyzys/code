package leetcode.tree._230

import scala.collection.mutable.ArrayBuffer

/**
 *
 * @author gzho
 * @since 2021-10-11 2:19 PM
 * @version 1.0.0
 */

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

/**
 * Definition for a binary tree node.
 * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
 * var value: Int = _value
 * var left: TreeNode = _left
 * var right: TreeNode = _right
 * }
 */
object Solution {

  val arr = new scala.collection.mutable.ArrayBuffer[TreeNode]()

  def kthSmallest(root: TreeNode, k: Int): Int = {
    inOrder(root)
    arr(k-1).value
  }

  def inOrder(pos:TreeNode): Unit ={
    if (pos==null)return
    if (pos.left!=null)inOrder(pos.left)
    arr.append(pos)
    if (pos.right!=null)inOrder(pos.right)
  }

  def main(args: Array[String]): Unit = {
    val node3: _root_.leetcode.tree._230.TreeNode = new TreeNode(3)
    val node1: _root_.leetcode.tree._230.TreeNode = new TreeNode(1)
    val node4: _root_.leetcode.tree._230.TreeNode = new TreeNode(4)
    val node2: _root_.leetcode.tree._230.TreeNode = new TreeNode(2)

    node3.left = node1
    node3.right = node4
    node1.right = node2

    println(kthSmallest(node3, 1))
  }
}

