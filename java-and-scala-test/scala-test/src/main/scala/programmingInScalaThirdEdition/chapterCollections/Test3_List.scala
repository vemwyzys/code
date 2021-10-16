package programmingInScalaThirdEdition.chapterCollections

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 2:19 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 2:19 下午
 */
object Test3_List {

  def main(args: Array[String]): Unit = {
    /**
     * 创建List
     * List是抽象类,不能new;使用伴生的apply生成
     */
    val ints: List[Int] = List(1, 2, 4, 5)

    /**
     * 访问和遍历
     */
    println(ints)
    println(ints(1))
    ints.foreach(println)
    //访问第一个元素
    val head: Int = ints.head
    //访问除第一个元素之后的列表
    val tail: List[Int] = ints.tail

    /**
     * 添加元素
     */
    val ints1: List[Int] = ints :+ (6)
    val ints2: List[Int] = 0 +: ints1 :+ 7

    val ints3: List[Int] = ints2.::(10)

    var newInts = Nil.::(14)
    val ints4: List[Int] = 10 :: 11 :: 10 :: Nil

    /**
     * List拼接
     * :::扁平化
     * ++ 等于:::
     */
    val ints5: List[Int] = ints ::: ints2
    val ints6: List[Int] = ints ++ ints2
  }

}

object Test {
  def main(args: Array[String]): Unit = {
    val feeds = List("blog.toolshed.com",
      "pragdave.me",
      "blog.agiledeveloper.com")


  }
}
