package programmingInScalaThirdEdition.chapter6_functionObject

/**
 * @describe 有理数
 * @author gzho
 * @since 2021-06-18 10:42 下午
 * @version 1.0.0
 * @updateTime 2021-06-18 10:42 下午
 */
class Rational(n: Int, d: Int) {

  //precondition
  require(d != 0)

  //g numer denom 三个字段将按照顺序依次初始化
  //初始化器:初始化某个变量的代码
  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  //auxiliary constructor辅助构造器
  def this(n: Int) = this(n, 1)

  def this(n: Long) = {
    //辅助构造函数的第一句一定是辅助构造或者主构造函数
    //    println("1")
    this(n.toInt, 1)
  }

  println("Created" + numer + "/" + denom)

  override def toString: String = numer + "/" + denom

  //辗转相除法
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  def +(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def lessThan(that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) =
    if (lessThan(that)) that else this

  def *(that: Rational) =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.numer
    )

  def +(num: Int) =
    new Rational(
      this.numer * num,
      this.denom
    )
}
