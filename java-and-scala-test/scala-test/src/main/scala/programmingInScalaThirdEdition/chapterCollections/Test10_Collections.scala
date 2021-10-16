package programmingInScalaThirdEdition.chapterCollections

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 7:51 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 7:51 下午
 */
object Test10_Collections {

  def main(args: Array[String]): Unit = {

    val list1: List[Int] = List(1, 2, 3, 4)

    val list2: List[Int] = List(11, 23, 39, 40)

    /**
     * 拉链
     */
    val tuples: List[(Int, Int)] = list1.zip(list2)

    /**
     * 滑窗
     */
    for (it <- list1.sliding(2)) {
      val it1: List[Int] = it
      println(it1 mkString "_")
    }
  }

}
