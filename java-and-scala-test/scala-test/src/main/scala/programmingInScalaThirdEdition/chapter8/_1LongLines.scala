package programmingInScalaThirdEdition.chapter8

import scala.io.{BufferedSource, Source}

/**
 * @describe
 * @author gzho
 * @since 2021-07-28 3:25 下午
 * @version 1.0.0
 * @updateTime 2021-07-28 3:25 下午
 */
object _1LongLines {

  def processFile(filename: String, width: Int): Unit = {
    val source: BufferedSource = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }

  private def processLine(filename: String, width: Int, line: String): Unit = {
    if (line.length > width)
      println(filename + ":" + line.trim)
  }

}

/**
 * 测试上一个object
 */
object FindLongLines {
  def main(args: Array[String]): Unit = {
    val width = args(0).toInt
    for (arg <- args.drop(1))
      _1LongLines.processFile(arg, width)
  }
}

/**
 * 改进_1LongLines
 * 1.私有方法变成局部函数
 * 2.移除冗余的参数
 */
object _2LongLines {

  def processFile(filename: String, width: Int): Unit = {
    /**
     * 局部函数
     *
     * @param line
     */
    def processLine(line: String): Unit = {
      if (line.length > width)
        println(filename + ":" + line.trim)
    }

    val source: BufferedSource = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }


}
