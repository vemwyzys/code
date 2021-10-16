package programmingInScalaThirdEdition.chapter10

/**
 * @describe 带有工厂方法的工厂对象
 * @author gzho
 * @since 2021-06-23 14:23
 * @version 1.0.0
 * @updateTime 2021-06-23 14:23
 */
object ElementTest {

  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)

  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)

  def elem(line: String): Element =
    new LineElement(line)


  def main(args: Array[String]): Unit = {
    val e: Element = new ArrayElement(Array("hello"))

    val lineElement2: Element = new LineElement2("11")
  }
}

/**
 * Element抽象类
 */
abstract class Element {

  /**
   * 返回字符串数组
   * 这是个没有被实现的方法,是个抽象方法
   * 不需要加上abstract关键字
   *
   * @return
   */
  def contents: Array[String]

  /**
   * 也可以将上面的函数改成字段
   */
  //val contents: Array[String]

  /**
   * 无参方法, 可以不用写()
   * 对于没有参数且只通过读取使用, 推荐使用无参方法不写空圆括号
   * 如果有副作用,建议加上()
   *
   * @return
   */
  def height: Int = contents.length

  //可以写成 val height = contents.length

  /**
   * 无参方法, 可以不用写()
   * 对于没有参数且只通过读取使用, 推荐使用无参方法不写空圆括号
   * 如果有副作用,建议加上()
   *
   * @return
   */
  def width: Int = if (height == 0) 0 else contents(0).length

  //可以写成 val width: Int = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element =
    new ArrayElement(this.contents ++ that.contents)

  /**
   * 合并两个element的对应位置的字符串为新字符串
   * 指令式
   *
   * @param that
   * @return
   */
  def beside(that: Element): Element = {
    val contents = new Array[String](this.contents.length)
    for (i <- 0 until this.contents.length)
      contents(i) = this.contents(i) + that.contents(i)
    new ArrayElement(contents)
  }

  /**
   * 同上
   * 声明式
   * 避免了显式的指针
   *
   * @param that
   * @return
   */
  def besideSimple(that: Element): Element =
    new ArrayElement(
      for (
        //zip操作符使得元素配对,多余的元素会被舍弃
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2  //yield的返回值同被遍历的元素,这里是(line1,line2) 也就是数组
    )

  /**
   * toString无参数,  不需要()
   * 符合统一访问原则
   *
   * @return
   */
  override def toString: String = contents mkString "\n"
}

/**
 * 有代码的坏味道, 因为类参数conts和contents类似 且conts仅仅只为了给contents赋值
 * 所以更改为ArrayElement2(参数化字段)
 *
 * @param conts
 */
class ArrayElement(conts: Array[String]) extends Element {

  //  override def contents: Array[String] = conts

  //重写[函数]->[字段]
  //可以用字段重写方法
  override val contents: Array[String] = conts

  //java有四个命名空间: 字段 方法  类型  包
  //scala只有两个命名空间 : 值(字段/方法/包/单例对象), 类型(类/特质名)
  //这样scala允许你使用val来重写一个无参数方法
}

/**
 * parametric field 参数化字段
 *
 * @param contents
 */
class ArrayElement2(val contents: Array[String]) extends Element

/**
 * 单行element
 * 继承超类 并调用超类主构造函数
 *
 * @param s
 */
class LineElement(s: String) extends ArrayElement(Array(s)) {
  //重写了从Element继承的width具体定义
  override def width: Int = s.length

  //重写了从Element继承的height具体定义
  override def height: Int = 1
}

/**
 * 使用指定的ch字符来创建指定宽度和高度的element
 *
 * @param ch
 * @param width
 * @param height
 */
class UniformElement(final val ch: Char,
                     override val width: Int,
                     override val height: Int
                    ) extends Element {
  private val line = ch.toString * width

  override def contents: Array[String] = Array.fill(height)(line)
}

/**
 * 改进LineElement
 * 跳过ArrayElement, 直接继承Element
 *
 * @param s
 */
class LineElement2(s: String) extends Element {

  override val contents: Array[String] = Array(s)

  override def height: Int = 1

  override def width: Int = s.length
}
