/**
 *
 * @author gzho
 * @since 2021-10-04 1:33 PM
 * @version 1.0.0
 */
object Test {

  def main(args: Array[String]): Unit = {
    val ints: List[Int] = 1 :: 2 :: Nil
    println(ints)

    var num = 10;
    for (i <- 0 until 10) {
      println(i)
    }
  }

}

/**
 * Definition for singly-linked list.
 * class ListNode(_x: Int = 0, _next: ListNode = null) {
 * var next: ListNode = _next
 * var x: Int = _x
 * }
 */

class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x

  def getNextInfo = {
    if (next == null) {
      ":null"
    } else {
      next.toString
    }
  }

  override def toString: String = "listNode value = " + x + " next:" + getNextInfo
}

object Solution {
  def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
    val dummy = new ListNode(-1)
    dummy.next = head
    val prevTarget: ListNode = findDescKNode(dummy, n + 1)
    prevTarget.next = prevTarget.next.next
    dummy.next
  }

  def findDescKNode(head: ListNode, k: Int) = {
    var up: ListNode = head
    for (i <- 0 until k) {
      up = up.next
    }
    var down: ListNode = head
    while (up != null) {
      up = up.next
      down = down.next
    }
    down
  }

  def reverseList(head: ListNode): ListNode = {
    var prev: ListNode = null
    var curr: ListNode = head
    while (curr != null) {
      val next= curr.next
      curr.next = prev
      prev = curr
      curr = next
    }
    prev
  }

  def main(args: Array[String]): Unit = {
    val node1: ListNode = new ListNode(1)
    val node2: ListNode = new ListNode(2)
    val node3: ListNode = new ListNode(3)
    val node4: ListNode = new ListNode(4)
    val node5: ListNode = new ListNode(5)
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5

    println(node1)

    //    println(findDescKNode(node1, 2))

    removeNthFromEnd(node1, 2)

    val nodeOne: ListNode = new ListNode(1)

    removeNthFromEnd(nodeOne, 1)


    val node11: ListNode = new ListNode(11)
    val node22: ListNode = new ListNode(12)
    node11.next = node22
    node22.next = node3

    println(GetIntersectionNode.getIntersectionNode(node1, node11))

    println("reverseList" + reverseList(node1))
  }
}

object GetIntersectionNode {
  def getIntersectionNode(headA: ListNode, headB: ListNode): ListNode = {
    var pa = headA
    var pb = headB
    while (pa != pb) {
      if (pa == null && pb == null) return null
      if (pa == null) pa = headB
      else pa = pa.next
      if (pb == null) {
        pb == headA
      }
      else pb = pb.next
    }
    pa
  }

  def getIntersectionNode2(headA: ListNode, headB: ListNode): ListNode = {
    val set = scala.collection.mutable.Set[ListNode]()
    while (headA != null) {
      set.add(headA)
    }
    while (headB != null) {
      if (set.contains(headB)) return headB
    }
    null
  }
}
