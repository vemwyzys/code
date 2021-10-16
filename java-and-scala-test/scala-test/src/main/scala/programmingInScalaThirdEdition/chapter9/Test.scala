package programmingInScalaThirdEdition.chapter9

import java.io.{File, PrintWriter}
import java.util.Date

/**
 * @describe
 * @author gzho
 * @since 2021-06-23 10:04
 * @version 1.0.0
 * @updateTime 2021-06-23 10:04
 */
object Test {

  def main(args: Array[String]): Unit = {

    def filesHere = new File(".").listFiles()

    /**
     * 一代目
     * 判断文件名
     *
     * @param query
     * @return
     */
    def filesEnding(query: String) =
      for (file <- filesHere; if file.getName.endsWith(query))
        yield file

    /**
     * 二代目
     * 接收函数作为参数
     * 功能: 查找文件名称符合条件的文件
     *
     * @param query
     * @param matcher
     * @return
     */
    def filesMatcher(query: String,
                     matcher: (String, String) => Boolean) =
      for (file <- filesHere; if matcher(file.getName, query))
        yield file

    /**
     * 使用
     */
    filesMatcher("abc", (fileName: String, query: String) => fileName.endsWith(query))
    //简化使用
    filesMatcher("abc", (fileName, query) => fileName.endsWith(query))
    //再简化使用
    filesMatcher("abc", _.endsWith(_))

    /**
     * 最终简化API
     *
     * @param matcher
     * @return
     */
    def filesMatcher2(matcher: String => Boolean) =
      for (file <- filesHere; if matcher(file.getName))
        yield file

    //最终简化使用
    filesMatcher2(_.contains("abc"))

    /**
     * 判断数组是否有负数
     *
     * @param nums
     * @return
     */
    def containsNeg(nums: List[Int]): Boolean = {
      var flag: Boolean = false
      for (num <- nums)
        if (num < 0)
          flag = true
      flag
    }
    //使用
    containsNeg(List(1, 2, -3, 4))

    /**
     * 使用高阶函数
     * ps: 那么一个函数就可以接收另一个函数作为参数，这种函数就称之为高阶函数。
     *
     * @param nums
     * @return
     */
    def containsNeg2(nums: List[Int]) = nums.exists(_ > 0)
    //使用
    containsNeg2(List(1, 3, -9, 1))

    /**
     * 重复两次操作
     * 参数格式可写作(op: (Double => Double), x: Double)
     *
     * @param op Double=>Double的lambda表达式
     * @param x  双精度
     * @return
     */
    def twice(op: Double => Double, x: Double) = op(op(x))

    /**
     * 向文件写东西
     *
     * 打开某个资源并将资源贷出给函数op
     * 函数会确保最后关闭文件, 这种技巧被称为贷出模式(loan pattern)
     *
     * @param file
     * @param op
     */
    def withPrintWriter(file: File, op: PrintWriter => Unit) = {
      val pw = new PrintWriter(file)
      try {
        op(pw)
      } finally {
        pw.close()
      }
    }

    /**
     * 柯里化
     *
     * @param file
     * @param op
     */
    def withPrintWriter2(file: File)(op: PrintWriter => Unit) = {
      val writer: PrintWriter = new PrintWriter(file)
      try {
        op(writer)
      } finally {
        writer.close()
      }
    }

    //向文件写日期
    withPrintWriter(new File("date.txt"),
      printWriter => printWriter.println(new Date())
    )

    //没有柯里化
    def plainOldSum(x: Int, y: Int) = x + y

    //currying 柯里化
    def curriedSum(x: Int)(y: Int) = x + y

    val x = curriedSum(1)(2)

    val firstCurried = curriedSum(1) _

    println(firstCurried)

    def first(x: Int) = (y: Int) => x + y

    val second = first(1)

    val result = second(2)

    /**
     * 将上面函数柯里化
     * 单独参数可以使用{}花括号
     *
     * @param file
     * @param op
     */
    def withPrintWriterCarried(file: File)(op: PrintWriter => Unit) = {
      val pw = new PrintWriter(file)
      try {
        op(pw)
      } finally {
        pw.close()
      }
    }
    //使用   第二个参数使用{}
    withPrintWriterCarried(new File("date2.txt")) { _ => println(new Date()) }
    withPrintWriterCarried(new File("date2.txt")) { writer => writer.println(new Date()) }

    /**
     * 传名函数
     *
     * @param predicate
     */
    //不使用传名参数
    def myAssert(predicate: () => Boolean) =
      if (!predicate())
        throw new AssertionError()

    myAssert(() => 5 < 2)

    //使用传名参数  by-name可以省略上例子的()
    def myAssert2(predicate: => Boolean) =
      if (!predicate)
        throw new AssertionError()

    myAssert2(5 > 2)
  }


}
