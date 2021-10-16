package programmingInScalaThirdEdition.other

/**
 * @describe
 * @author gzho
 * @since 2021-08-08 6:36 下午
 * @version 1.0.0
 * @updateTime 2021-08-08 6:36 下午
 */
object OptionTest {

  /**
   * Some和None都是Option的子类
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    def commentOnPractice(input: String) = {
      if (input == "test") Some("good")
      else None
    }

    for (input <- Set("test", "hack")) {
      val maybeString: Option[String] = commentOnPractice(input)
      val str: String = maybeString.getOrElse("Found no comments")
      println(str)
    }

    def compute(input: Int) = {
      if (input > 0)
        Right(math.sqrt(input))
      else
        Left("Error computing, invalid input")
    }
  }

}
