package programmingInScalaThirdEdition.chapter15_match

/**
 * @describe
 * @author gzho
 * @since 2021-08-04 6:46 下午
 * @version 1.0.0
 * @updateTime 2021-08-04 6:46 下午
 */
abstract class Expr()

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class Unop(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object Test {
  def main(args: Array[String]): Unit = {
    /**
     * 样例类会为类增加同名的工厂方法
     */
    val v = Var("x")

    /**
     * 样例类中的参数列表都隐式地获得了val前缀,被当做字段处理
     */
    v.name

    /**
     * 通配模式
     *
     * @param expr
     * @return
     */
    def simplifyTop(expr: Expr): Expr = expr match {
      //双重取负
      case Unop("-", Unop("-", e)) => e
      //加0
      case BinOp("+", e, Number(0)) => e
      //✖️1
      case BinOp("*", e, Number(1)) => e
      case _ => expr
    }

    /**
     * 常量模式
     * 因为常量是有限的,所以最后一定要有一个通配处理
     */
    def describe(x: Any) = x match {
      case 5 => "five"
      case true => "truth"
      case "hello" => "hi"
      case Nil => "the empty list"
      case _ => "something else"
    }

    /**
     * 匹配类型
     *
     * @param x
     */
    def matchType(x: Any) = x match {
      case i: Int => println("Int:" + i)
      case s: String => println("String:" + s)
      case m: Map[_, _] => m.size
      //由于集合的类型擦除,所以只匹配List,其中的String类型是不判断的,直接匹配成功
      case l: List[String] => println("list" + l)
      //Array是底层数据结构, 所有会判断其中所含有的数据类型
      case a: Array[String] => println("array [string]" + a)
      case a: Array[Int] => println("array [Int]" + a)
      //不清楚的值,直接赋值
      case somethingElse => println("something else" + somethingElse)
      //通配符_  只能得到与输入值无关的返回;
      // 而上面的somethingElse可以获得参数并输出
      case _ => println("通配符")
    }

    /**
     * 模式守卫
     * 1.匹配某个范围的数据
     */
    def like_abs(x: Int) = x match {
      case i if i > 10 => i
      case i if i < 10 && i > 0 => i
      case j if j <= 0 => -j
    }

    /**
     * 列表匹配
     * 下列两个match相同
     * 但
     * list1的first是1, second是2, rest是剩下的列表
     * list2不符合第一个case,所以会输出somethingElse
     */
    val list1: List[Int] = List(1, 2, 4, 55, 100, 200)
    val list2: List[Int] = List(10, 30)

    list1 match {
      case first :: second :: rest => println(s"first:$first, second:$second, rest:$rest")
      case somethingElse => println("somethingElse" + somethingElse)
    }

    list2 match {
      case first :: second :: rest => println(s"first:$first, second:$second, rest:$rest")
      case somethingElse => println("somethingElse" + somethingElse)
    }

    /**
     * 元组匹配
     */
    for (tuple <- List(
      (1, 2),
      (20, 100),
      (-1, 20),
      (100, 190),
      (1, 2, 4),
      (0, 0),
      ("hello", 1, true)
    )) {
      val result = tuple match {
        case (a, b) => "a:" + a + "b:" + b
        case (a, b, _) => "a:" + a + "b:" + b
        case (0, _) => "0" + _
        case _ => "somethingElse"
      }
      println(result)
    }

    val (x, y) = (11, "world")
    println("x:" + x + "Y:" + y)

    /**
     * fir被赋予11, sec被赋予22, rest被赋予List(33,44)
     */
    val fir :: sec :: rest = List(11, 22, 33, 44)

    val list: List[(Int, String)] = List((1, "a"), (2, "b"), (3, "c"))

    /**
     * 原本的遍历方式
     */
    for (elem <- list) {
      println(elem._1 + "," + elem._2)
    }

    /**
     * 将list的元素定义为元组, 对变量赋值
     */
    for ((num, str) <- list) {
      println(num + "" + str)
    }

    /**
     * 或者能只遍历一部分
     */
    for ((num, _) <- list) {
      println(num)
    }

    /**
     * 可以指定某个变量必须是多少
     * 这里指定元组的第一个位置必须是1
     */
    for ((1, str) <- list) {
      println(str)
    }

    /**
     * 序列模式 !有问题!
     */
//    val listNil: Nil.type = Nil
//    listNil match {
//      case List(0, _, _) => println("found it")
//      case _ =>
//    }
//
//    listNil match {
//      case List(0, _, _*) => println("found it")
//      case _ =>
//    }

    /**
     * 变量绑定
     * 如果匹配成功,则将匹配了的部分被赋值给变量
     */
    def varMatch(x: Any) = x match {
      case Unop("abc", e@Unop("abc", _)) => e.copy("", Number(1))
      //copy用于生成新对象, 并可以指定类参数
    }

  }
}
