package programmingInScalaThirdEdition.chapterCollections

/**
 * @describe
 * @author gzho
 * @since 2021-08-10 12:20 下午
 * @version 1.0.0
 * @updateTime 2021-08-10 12:20 下午
 */
object Test17_CommonWordCount {

  /**
   * word count 获取前三名
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val list: List[String] = List(
      "hello world",
      "water melon",
      "grapefruit is delicious",
      "js sucks",
      "colorful gay",
      "speech man",
      "my no no no day is boring",
      "sat navi",
      "iron man"
    )
    //切割&flat
    //    val strArrs: List[Array[String]] = list.map(_.split(" "))
    //    val words: List[String] = strArrs.flatten
    val words: List[String] = list.flatMap(_.split(" "))
    //group
    val groupMap: Map[String, List[String]] = words.groupBy(word => word)
    println(groupMap)
    //对分组后数据的list取长度
    val countMap: Map[String, Int] = groupMap.map(kv => (kv._1, kv._2.length))

    //对长度排序
    ///需要先将map转换为list
    val sortList: List[(String, Int)] = countMap
      .toList
      .sortWith(_._2 > _._2)
      .take(3)

    println(sortList)


  }

}
