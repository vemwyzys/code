package programmingInScalaThirdEdition.chapter_for

import scala.collection.immutable

/**
 * @describe
 * @author gzho
 * @since 2021-08-11 2:31 下午
 * @version 1.0.0
 * @updateTime 2021-08-11 2:31 下午
 */
object Test {

  def main(args: Array[String]): Unit = {

    /**
     * yield创建一个值列表
     */
    var ints: immutable.IndexedSeq[Int] =
      for (i <- 1 to 10) yield i * 2
    ints = (1 to 10).map(_ * 2)

    /**
     * list comprehension 列表推导
     * 创建值列表并过滤
     */
    var doubleEvens: immutable.IndexedSeq[Int] =
      for (i <- 1 to 10; if i % 2 == 0) yield i * 2
    //写成大括号形式, 并 ;=>\n
    doubleEvens = for {
      i <- 1 to 10
      if i % 2 == 0
    } yield i * 2

    /**
     * 双循环生成tuple
     */
    val tuples: immutable.IndexedSeq[(Int, Int)] =
      for (i <- 1 to 10; j <- 1 to 20) yield (i, j)
  }

}