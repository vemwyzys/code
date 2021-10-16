package programmingInScalaThirdEdition.chapterCollections

import scala.collection.mutable.ListBuffer

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 2:26 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 2:26 下午
 */
object Test4_ListBuffer {

  def main(args: Array[String]): Unit = {

    /**
     * 创建可变列表
     */
    var buffer: ListBuffer[Int] = new ListBuffer[Int]()
    buffer = ListBuffer(1, 2, 4)

    /**
     * 添加元素
     */
    buffer.append(5)
    buffer.prepend(0)

    buffer.insert(2, 99)

    println(buffer)

    buffer += 100 += 200
    println(buffer)

    -100 +=: -50 +=: buffer += 101 += 102
    println(buffer)

    /**
     * 修改元素
     */
    buffer(0) = 100
    buffer.update(0,200)

    /**
     * 删除元素
     */
    //去除0下标
    buffer.remove(0)
    //去除第一个100元素
    buffer -= 100

    /**
     * 合并listBuffer
     * 会生成一个新的listBuffer
     */
    val listBuffer: ListBuffer[Int] = buffer ++ buffer

  }

}
