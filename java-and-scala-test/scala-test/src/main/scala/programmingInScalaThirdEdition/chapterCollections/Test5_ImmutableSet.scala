package programmingInScalaThirdEdition.chapterCollections

/**
 * @describe
 * @author gzho
 * @since 2021-08-09 3:05 下午
 * @version 1.0.0
 * @updateTime 2021-08-09 3:05 下午
 */
object Test5_ImmutableSet {

  def main(args: Array[String]): Unit = {
    /**
     * 创建不可变set
     */
    val set: Set[Int] = Set(1, 2, 3, 4)

    /**
     * 添加元素
     */
    val set1: Set[Int] = set + 10

    /**
     * 合并set
     */
    val set2: Set[Int] = set ++ set1

    /**
     * 删除元素
     */
    val set3: Set[Int] = set - 3
  }

}
