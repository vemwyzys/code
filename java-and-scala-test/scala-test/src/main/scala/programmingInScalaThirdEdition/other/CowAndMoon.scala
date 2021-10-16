package programmingInScalaThirdEdition.other

/**
 * @describe
 * @author gzho
 * @since 2021-08-11 2:22 下午
 * @version 1.0.0
 * @updateTime 2021-08-11 2:22 下午
 */
object CowAndMoon {

  def main(args: Array[String]): Unit = {

    val cow: Cow = new Cow
    val moon: Moon = new Moon

    cow ^ moon

    cow ^: moon


    +cow
  }

}

class Cow {

  def ^(moon: Moon): Unit = {
    println("a cow jump over a moon")
  }

  def unary_+() = println("a cow plus!")
}

class Moon {

  def ^:(cow: Cow): Unit = {
    println("a cow jump over a moon")
  }

}
