package programmingInScalaThirdEdition.chapterCollections

/**
 * @describe
 * @author gzho
 * @since 2021-08-11 11:50 上午
 * @version 1.0.0
 * @updateTime 2021-08-11 11:50 上午
 */
object Test0_Set {

  def main(args: Array[String]): Unit = {

    /**
     * 不可变集合
     * 其中元素不能改变, 所以需要初始化时就指定
     */

    /**
     * 创建set
     */
    val feeds1: Set[String] = Set("blog.toolshed.com", "pragdave.me", "blog.agiledeveloper.com")
    val feeds2: Set[String] = Set("blog.toolshed.com", "martinfowler.com/bliki")
    println(s"feeds1 = ${feeds1}")
    val blog: Set[String] = feeds1 filter (_.contains("blog"))

    /**
     * 合并set
     */
    val feedMerge: Set[String] = feeds1 ++ feeds2
    println(s"feedMerge = ${feedMerge}")

    /**
     * 取交集
     */
    val sameSet: Set[String] = feeds1 & feeds2
    println(s"sameSet = ${sameSet}")

    /**
     * map
     */
    val feedsWithHttp: Set[String] = feeds1.map("http://" + _)
    println(s"feedsWithHttp = ${feedsWithHttp}")



  }

}
