package programmingInScalaThirdEdition.chapter10

/**
 * @describe 参数化字段
 * @author gzho
 * @since 2021-06-23 16:35
 * @version 1.0.0
 * @updateTime 2021-06-23 16:35
 */
class Cat {
  val dangerous = false
}

//简单写法
class tiger(
             override val dangerous: Boolean,
             private var age: Int
           ) extends Cat

//复杂写法
class TigerComplex(param1: Boolean, param2: Int) extends Cat {
  override val dangerous: Boolean = param1
  private var age: Int = param2
}

