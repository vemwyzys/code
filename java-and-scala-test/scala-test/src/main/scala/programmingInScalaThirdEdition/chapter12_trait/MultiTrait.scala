package programmingInScalaThirdEdition.chapter12_trait

import scala.collection.mutable.ArrayBuffer

/**
 * @describe
 * @author gzho
 * @since 2021-08-02 9:49 下午
 * @version 1.0.0
 * @updateTime 2021-08-02 9:49 下午
 */
object MultiTrait {

  def main(args: Array[String]): Unit = {

    val queue = new BasicIntQueue with Doubling
    queue.put(10)
    println(queue.get)

    //混入特质的顺序是重要的,越靠右越先
    val queue1 = new BasicIntQueue with Doubling with Incrementing with Filtering


  }
}

abstract class IntQueue {
  def get: Int

  def put(x: Int)
}

class BasicIntQueue extends IntQueue {

  private val buf = new ArrayBuffer[Int]

  override def get: Int = buf.remove(0)

  override def put(x: Int): Unit = {
    buf += x
  }
}

/**
 * 继承IntQueue超类,意味着Doubling只能只能混入同样继承自IntQueue的类
 */
trait Doubling extends IntQueue {
  /**
   * abstract代表 该特质还没有实现,所以该特质必须混入某个拥有该方法具体定义的类中
   * 此例子中就是必须继承IntQueue拥有其put方法
   *
   * @param x
   */
  abstract override def put(x: Int) = super.put(2 * x)
}

/**
 * 这个类无额外定义
 * 所有可以直接在代码中用 val queue = new BasicIntQueue with Doubling来替代
 */
class MyQueue extends BasicIntQueue with Doubling {

}

/**
 * 其他特质
 */
trait Incrementing extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(x + 1)
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int): Unit = if (x >= 0) super.put(x)
}

