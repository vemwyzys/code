package programmingInScalaThirdEdition.chapter11

/**
 * @describe
 * @author gzho
 * @since 2021-07-31 11:32 下午
 * @version 1.0.0
 * @updateTime 2021-07-31 11:32 下午
 */
object Test {

  def main(args: Array[String]): Unit = {
    val b: Boolean = true
  }

}

class Dollars(val amount: Int) extends AnyVal {
  override def toString = "$" + amount
}
