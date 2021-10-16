package programmingInScalaThirdEdition.chapterCollections

import scala.+:
import scala.collection.mutable

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 9:42 上午
 * @version 1.0.0
 * @updateTime 2021-08-09 9:42 上午
 */
object Test1_Array {

  def main(args: Array[String]): Unit = {

    //Array的实现里方法都抛出了异常, 实际上是存根方法,
    // 编译器会将其替

    /**
     * 创建Array
     */
    val arr = new Array[Int](5)
    val arr1 = Array(12, 13, 14, 15)

    /**
     * 访问元素
     */
    println(arr(0))

    println("========================")

    /**
     * 遍历
     */
    for (i <- 0 until arr1.length) println(i)
    for (i <- arr1) {
      println(i)
    }
    arr1.foreach((elem: Int) => println(elem))
    arr1.foreach(elem => println(elem))
    arr1.foreach(println)
    println(arr1.mkString("--"))

    println("========================")

    /**
     * 修改元素
     */
    arr(0) = 100
    println(arr(0))

    println("========================")

    /**
     * 添加元素
     */
    val newArr1: Array[Int] = arr1.:+(89)
    val newArr2: Array[Int] = newArr1.+:(99)
    val na1: Array[Int] = arr1 :+ 89
    val na2: Array[Int] = 99 +: arr1

    for (arr <- List(newArr1, newArr2, na1, na2)) {
      println(arr.mkString("--"))
    }

    /**
     * 不可变数组 转 可变数组
     */
    val buffer: mutable.Buffer[Int] = newArr2.toBuffer

  }

}
