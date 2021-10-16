package programmingInScalaThirdEdition.chapter12_trait

/**
 * @describe
 * @author gzho
 * @since 2021-08-01 9:47 上午
 * @version 1.0.0
 * @updateTime 2021-08-01 9:47 上午
 */
object Test {

  def main(args: Array[String]): Unit = {
    val frog: Philosophical = new Frog()
    println(frog.philosophize())
  }
}


trait Philosophical {
  /**
   * 特质trait拥有类似java8接口中的默认方法
   */
  def philosophize() = {
    println("I consume memory, therefore I am")
  }
}

class Frog extends Philosophical {
  override def toString: String = "green"
}

class Animal {

}

class Frog2 extends Animal with Philosophical {
  override def toString: String = "green"
}

class Point(val x: Int, val y: Int) {

}

trait Rectangular {
  def topLeft: Point

  def bottomRight: Point

  def left = topLeft.x

  def right = bottomRight.x

  def width = right - left
}

class Rational(n: Int, d: Int) extends Ordered[Rational] {
  override def compare(that: Rational): Int = ???
}

