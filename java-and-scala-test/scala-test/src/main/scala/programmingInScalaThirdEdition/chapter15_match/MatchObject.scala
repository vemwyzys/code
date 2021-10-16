package programmingInScalaThirdEdition.chapter15_match

/**
 * @describe
 * @author gzho
 * @since 2021-08-06 4:59 下午
 * @version 1.0.0
 * @updateTime 2021-08-06 4:59 下午
 */
object MatchObject {

  def main(args: Array[String]): Unit = {

    val mary: Student = Student(19, "mary")

    /**
     * 原始
     * 针对对象实例的内容进行匹配
     */
    mary match {
      case Student(19, "mary") => println("19, mary")
      case _ => "else"
    }

    /**
     * 样例类进行模式匹配
     */
    val nick: StudentCase = StudentCase("nick", 20)

    nick match {
      case StudentCase("nick", 20) => println("nick 20")
      case _ => "else"
    }

  }

}

class Student(val age: Int, val name: String)

/**
 * 定义伴生对象
 */
object Student {
  def apply(age: Int, name: String) = new Student(age, name)

  /**
   * 另外需要实现unapply方法才能对实例内容进行匹配
   *
   * @param student
   * @return
   */
  def unapply(student: Student): Option[(Int, String)] = {
    if (student == null) {
      None
    } else {
      Some((student.age, student.name))
    }
  }
}

/**
 * 定义样例类
 * .自动类参数成为成员变量
 * .自动生成伴生对象
 * .apple&unapply方法
 * @param name
 * @param age
 */
case class StudentCase(name: String, age: Int)
