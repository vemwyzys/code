package programmingInScalaThirdEdition.chapterCollections

import scala.collection.mutable.ArrayBuffer

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 11:39 上午
 * @version 1.0.0
 * @updateTime 2021-08-09 11:39 上午
 */
object Test2_ArrayBuffer {

  def main(args: Array[String]): Unit = {

    /**
     * 创建可变数组
     */
    val bu1: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    val bu2: ArrayBuffer[Int] = ArrayBuffer(22, 44, 66)

    /**
     * 访问元素
     */
    println(bu2(1))

    /**
     * 修改元素
     */
    bu2(0) = 100
    println(bu2)

    /**
     * 添加元素
     */
    //符号操作依然会创建给新数组
    bu2 :+ 99
    println(bu2.mkString("_"))
    bu2 += 99
    println(bu2.mkString("_"))

    1000 +=: bu2
    println(bu2 mkString "_")

    bu2.append(88)
    bu2.prepend(88)
    bu2.insert(2, 0)
    println(bu2 mkString "_")

    /**
     * 删除元素
     */
    //删除首个88这个元素
    bu2 -= 88
    println(bu2 mkString "_")
    bu2 -= 88
    println(bu2 mkString "_")
    bu2.remove(2)
    bu2.remove(2, 2)
    println(bu2 mkString "_")

    /**
     * 可变数组 转 不可变数组
     */
    val array: Array[Int] = bu2.toArray

  }

}
