package programmingInScalaThirdEdition.chapterCollections

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 8:02 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 8:02 下午
 */
object Test13_SimpleFunction {

  def main(args: Array[String]): Unit = {

    val list: List[Int] = List(6, 1, 3, 19)
    val list1: List[(String, Int)] = List(("a", 11), ("b", 2), ("c", 3))

    /**
     * 求和
     */
    val sum: Int = list.sum

    /**
     * 最值
     */
    var max: (String, Int) = list1.maxBy((t: (String, Int)) => t._2)
    max = list1.maxBy(_._2)

    /**
     * 排序
     */
    val sortedSmall: List[Int] = list.sorted
    val sortedBig: List[Int] = list.sorted(Ordering[Int].reverse)
    println(list1.sortBy(_._2)(Ordering[Int].reverse))
    //比较器
    list1.sortWith((a: (String, Int), b: (String, Int)) => a._1 > b._1)
    list1.sortWith((a, b) => a._1 > b._1)
    list1.sortWith(_._1 > _._1)

  }

}
