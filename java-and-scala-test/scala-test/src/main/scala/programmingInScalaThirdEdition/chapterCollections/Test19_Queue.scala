package programmingInScalaThirdEdition.chapterCollections

import scala.collection.immutable.Queue
import scala.collection.mutable
import scala.collection.parallel.immutable

/**
 * @describe
 * @author gzho
 * @since 2021-08-10 2:21 下午
 * @version 1.0.0
 * @updateTime 2021-08-10 2:21 下午
 */
object Test19_Queue {

  def main(args: Array[String]): Unit = {

    /**
     * 创建可变队列
     */
    var que: mutable.Queue[String] = new mutable.Queue[String]()
    que = mutable.Queue("abc", "bcd", "nick")

    que.enqueue("brilliant","beautiful")

    val dequeue1: String = que.dequeue()
    println(dequeue1)
    val dequeue2: String = que.dequeue()
    println(dequeue2)

    /**
     * 创建不可变队列
     */
    val ique: Queue[String] = Queue("a", "b", "c")
    //不可变queue,需要新queue来接收
    val ique2: Queue[String] = ique.enqueue("d")
    println(ique)
    println(ique2)


  }

}
