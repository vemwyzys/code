package programmingInScalaThirdEdition.chapterCollections

import scala.collection.mutable

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 6:23 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 6:23 下午
 */
object Test8_MutableMap {

  def main(args: Array[String]): Unit = {
    /**
     * 创建
     */
    val map: mutable.Map[String, Int] = mutable.Map("a" -> 1, "b" -> 2)


    /**
     * 添加元素
     */
    map.put("c", 4)
    map += (("e", 8))

    /**
     * 删除元素
     */
    map.remove("e")
    map -= "c"
    println(map)

    /**
     * 修改(插入)元素
     */
    map.update("d", 8)
    println(map)

    /**
     * 合并map
     */
    val map1: Map[String, Int] = Map("a" -> 1, "b" -> 2, "c" -> 10)
    map ++= map1
    println(map)

    val option: Option[Int] = map.get("a")
    if (option.isDefined) {

    }

  }

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val map = scala.collection.mutable.Map[Int, Int]()
    for (i <- nums.indices) {
      map.put(nums(i), i)
    }
    for (i <- 0 until nums.length) {
      val other = target - nums(i)
      val result = map.get(other)
      if (result.isDefined) {
        return Array(i, result.get)
      }
    }
    Array(-1, -1)

  }


}

/**
 * Definition for a Node.
 * class Node(var _value: Int) {
 * var value: Int = _value
 * var left: Node = null
 * var right: Node = null
 * var next: Node = null
 * }
 */

class Node(var _value: Int) {
  var value: Int = _value
  var left: Node = null
  var right: Node = null
  var next: Node = null
}

object Solution {
  def connect(root: Node): Node = {
    if (root == null) return root
    connectTwoNode(root.left, root.right)
    return root
  }

  def connectTwoNode(a: Node, b: Node):Unit = {
    if (a == null || b == null) {
      return
    }
    a.next = b
    connectTwoNode(a.left, a.right)
    connectTwoNode(b.left, b.right)
    connectTwoNode(a.right, b.right)
    Int.MaxValue
  }
}

