package programmingInScalaThirdEdition.chapterCollections

/**
 * @describe
 * @author gzho
 * @since 2021-08-10 1:01 下午
 * @version 1.0.0
 * @updateTime 2021-08-10 1:01 下午
 */
object Test18_ComplexWordCount {

  def main(args: Array[String]): Unit = {

    val list: List[(String, Int)] = List(
      ("hello world", 2),
      ("water melon", 5),
      ("grapefruit is delicious", 3),
      ("js sucks", 2),
      ("colorful gay", 7),
      ("speech man", 2),
      ("my no no no day is boring", 7),
      ("sat navi", 5),
      ("iron man", 11)
    )

    /**
     * 思路一:将所有次数展开,按普通统计来操作
     */
    val redundancyList: List[String] = list.map(kv => {
      (kv._1.trim + " ") * kv._2
    })
    println(redundancyList)
    val wordCountList: List[(String, Int)] = redundancyList
      .flatMap(_.split(" "))
      .groupBy(word => word)
      .map(kv => (kv._1, kv._2.length))
      .toList
      .sortBy(_._2)(Ordering[Int].reverse)
      .take(3)
    println(wordCountList)

    println("========================")

    /**
     * 思路二:直接基于预统计的结果来word_count
     */
    //将字符串打散,并包装成元组
    val countList: List[(String, Int)] = list.flatMap(
      tuple => {
        val strs: Array[String] = tuple._1.split(" ")
        val tuples: Array[(String, Int)] = strs.map(word => (word, tuple._2))
        println(tuples)
        tuples
      }
    )
    println(countList)
    val countGroup: Map[String, Int] = countList
      //对二元组单词进行分组 :Map[String, List[(String, Int)]]
      .groupBy(_._1)
      //叠加每个单词统计的数值
      .mapValues(
        tupleList => tupleList.map(_._2).sum
      )
    //      .transform(
    //        (k, v) =>v.reduce(v)
    //
    //      )
    println(countGroup)
    //转换成list,取前3
    println(countGroup.toList.sortWith(_._2 > _._2).take(3))

  }

}
