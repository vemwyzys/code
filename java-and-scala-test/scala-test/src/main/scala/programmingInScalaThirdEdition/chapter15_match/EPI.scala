package programmingInScalaThirdEdition.chapter15_match

import math.{E, Pi}

/**
 * @describe
 * @author gzho
 * @since 2021-08-05 10:46 下午
 * @version 1.0.0
 * @updateTime 2021-08-05 10:46 下午
 */
object EPI {

  def main(args: Array[String]): Unit = {
    val str: String = E match {
      case Pi => "strange math?"
      case _ => "OK"
    }
  }

}
