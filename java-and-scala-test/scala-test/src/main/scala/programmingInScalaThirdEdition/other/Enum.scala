package programmingInScalaThirdEdition.other

/**
 * @describe 枚举
 * @author gzho
 * @since 2021-08-08 3:04 下午
 * @version 1.0.0
 * @updateTime 2021-08-08 3:04 下午
 * 参考
 * @https://www.dazhuanlan.com/ydby/topics/952025
 */
object Currency extends Enumeration {
  /**
   * Value在超类中定义
   */
  type Currency = Value
  val CNY, GBP, INR, JPY, USD = Value
}

object Test{
  def main(args: Array[String]): Unit = {
    Currency.USD
  }
}
