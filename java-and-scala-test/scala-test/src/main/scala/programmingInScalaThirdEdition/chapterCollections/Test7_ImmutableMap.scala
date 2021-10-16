package programmingInScalaThirdEdition.chapterCollections

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 3:53 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 3:53 下午
 */
object Test7_ImmutableMap {

  def main(args: Array[String]): Unit = {
    /**
     * 创建map
     */
    val map: Map[String, Int] = Map("a" -> 1, "b" -> 2, "c" -> 10)

    /**
     * 遍历map
     */
    map.foreach(println)
    map.foreach((kv: (String, Int)) => println(kv))

    /**
     * 获取value
     */
    val maybeInt: Option[Int] = map.get("a")
    val value1: Int = map.get("a").get
    val value2: Int = map("a")

    /**
     * 取所有key 或 value
     */
    for (key <- map.keys) println(key)
    for (value <- map.values) println(value)

    /**
     * 合并map
     */
    val map1: Map[String, Int] = map ++ map



    val feeds = Map(
      "Andy Hunt" -> "blog.toolshed.com",
      "Dave Thomas" -> "pragdave.me",
      "NFJS" -> "nofluffjuststuff.com/blog")
    /**
     * 选取名字以D开头的key
     */
    val feedWithDStart: Map[String, String] = feeds.filterKeys(_.startsWith("D"))
    println(s"feedWithDStart = ${feedWithDStart}")

    /**
     * 过滤
     */
    println(feeds.filter(elem => {
      elem._1.startsWith("D") && elem._2.contains("me")
    }))

  }

}