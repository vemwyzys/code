package programmingInScalaThirdEdition.chapterCollections

import scala.collection.mutable

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 11:38 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 11:38 下午
 */
object Test15_Reduce {

  def main(args: Array[String]): Unit = {

    val list: List[Int] = List(1, 2, 3, 4)

    /**
     * reduce
     */
    list.reduce((a, b) => a + b)
    val i1: Int = list.reduce(_ + _)
    val i2: Int = list.reduceLeft(_ - _)
    //递归调用1-(2-(3-4))
    val i3: Int = list.reduceRight(_ - _)

    /**
     * fold
     * 给定初始值的聚合
     */
    val i: Int = list.fold(10)(_ + _)
    //注意其中顺序
    val i4: Int = list.foldLeft(10)(_ - _)

    /**
     * map合并归约
     * 相同key的value相加
     */
    val map1: mutable.Map[String, Int] = mutable.Map("a" -> 1, "b" -> 2, "c" -> 10)
    val map2: mutable.Map[String, Int] = mutable.Map("a" -> 11, "b" -> 23, "c" -> 100, "d" -> 200)

    val mergeMap: mutable.Map[String, Int] = map1.foldLeft(map2)(
      (mergerMap, kv) => {
        val key: String = kv._1
        val value: Int = kv._2
        mergerMap(key) = mergerMap.getOrElse(key, 0) + value
        mergerMap
      }
    )
    println(mergeMap)

  }

}
