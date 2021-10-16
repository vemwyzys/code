package leetcode.tree

/**
 *
 * @author gzho
 * @since 2021-10-10 5:57 PM
 * @version 1.0.0
 */
object _645 {

  def main(args: Array[String]): Unit = {
    constructMaximumBinaryTree(Array(0,2,5,9,99,100))
  }

  def constructMaximumBinaryTree(nums: Array[Int]): TreeNode = {
    build(nums, 0, nums.length - 1)
  }

  def build(nums: Array[Int], l: Int, r: Int): TreeNode = {
    if (l > r) return null
    //find the max element int the array
    var max = Int.MinValue
    var index = 0
    for (i <- l to r) {
      if (nums(i) > max) {
        index = i
        max = nums(i)
      }
    }
    val root = new TreeNode(max)
    root.left = build(nums, l, index - 1)
    root.right = build(nums, index + 1, r)
    root
  }


}

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}
