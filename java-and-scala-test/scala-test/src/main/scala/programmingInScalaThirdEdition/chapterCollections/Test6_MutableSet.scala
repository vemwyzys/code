package programmingInScalaThirdEdition.chapterCollections

import scala.collection.mutable

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 3:38 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 3:38 下午
 */
object Test6_MutableSet {

  def main(args: Array[String]): Unit = {
    /**
     * 创建可变Set
     */
    val set: mutable.Set[Int] = mutable.Set(1, 2, 3, 4, 5)

    /**
     * 添加元素
     */
    set.add(6)
    set += 7
    println(set)

    /**
     * 删除元素
     */
    set -= 7
    println(set)
    set.remove(7)

    /**
     * 合并Set
     */
    val set1: mutable.Set[Int] = set ++ mutable.Set(9, 10)
    println(set1)
    //++=  改变自己合并
    set ++= mutable.Set(1100,200)
    println(set)

    var set2: mutable.Set[Int] = scala.collection.mutable.Set[Int]()
  }

}
