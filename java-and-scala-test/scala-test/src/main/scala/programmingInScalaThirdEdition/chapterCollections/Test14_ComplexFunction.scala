package programmingInScalaThirdEdition.chapterCollections

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 8:02 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 8:02 下午
 */
object Test14_ComplexFunction {

  def main(args: Array[String]): Unit = {

    val list: List[Int] = List(6, 1, 3, 19)
    val list1: List[(String, Int)] = List(("a", 11), ("b", 2), ("c", 3))

    /**
     * 过滤
     */
    val over5List: List[Int] = list.filter(_ > 5)
    val evenList: List[Int] = list.filter(_ % 2 == 1)

    /**
     * map
     */
    val doubleList: List[Int] = list.map(_ * 2)
    val persons: List[Person] = list.map(new Person(_))

    /**
     * flatMap
     */
    val nestedList: List[List[Int]] = List(List(1, 2), List(3, 4), List(8, 9))
    println(nestedList.flatten)
    //将一组字符串进行分词,并保存单词的列表
    val strings: List[String] = List("hello world", "wo cao", "ex pr", "hook")
    val strArrBySpace: List[Array[String]] = strings.map(_.split(" "))
    var strFlatten: List[String] = strArrBySpace.flatten
    strFlatten = strings.flatMap(_.split(" "))

    /**
     * groupBy
     */
    //分成奇偶两组
    list.groupBy(data => data % 2)
    list.groupBy(_ % 2)
    //按首字母分组
    println(strings.groupBy(_.charAt(0)))
  }

}

class Person(val age: Int)
