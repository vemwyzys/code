package programmingInScalaThirdEdition.chapterCollections

import scala.collection.immutable
import scala.collection.parallel.immutable.ParSeq

/**
 * @describe
 * @author gzho
 * @since 2021-08-10 2:28 下午
 * @version 1.0.0
 * @updateTime 2021-08-10 2:28 下午
 */
object Test20_Parallel {

  def main(args: Array[String]): Unit = {
    val result: immutable.IndexedSeq[String] = (1 to 100).map(
      x => Thread.currentThread.getName
    )
    println(result)

    val resultP: ParSeq[String] = (1 to 100).par.map(
      x => Thread.currentThread.getName
    )
    println(resultP)
  }

}
