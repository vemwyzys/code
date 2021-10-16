package programmingInScalaThirdEdition.chapterCollections

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 6:46 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 6:46 下午
 */
object Test9_Tuple {

  def main(args: Array[String]): Unit = {

    /**
     * 创建元素
     */
    val tuple: (String, Int, Boolean) = ("hl", 4, true)
    println(tuple)

    /**
     * 访问数据
     */
    val value: String = tuple._1 //第一个元素
    val value1: Int = tuple._2 //第二个元素
    val value2: Any = tuple.productElement(0) //第一个元素,下标为0

    /**
     * 遍历元组数据
     */
    for (elem <- tuple.productIterator)
      println(elem)

    /**
     * 嵌套元组
     */
    val tuple1: (Int, Boolean, (Int, String), Long) = (12, true, (1, "sick"), 1L)
    val value3: Int = tuple1._3._1
  }

}
