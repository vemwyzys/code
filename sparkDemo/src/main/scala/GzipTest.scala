import org.apache.spark.sql.{SaveMode, SparkSession}

import java.net.URL
import scala.io.Source

object GzipTest {

  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder()
      .appName("gzipTest")
      .master("local")
      .getOrCreate()
    //    val it = Source.fromResource("test.csv").getLines()
    //    while (it.hasNext) println(it.next())

    val path = getClass.getResource("/test.csv").getPath

    ss.read
      .option("header", "true")
      .csv(path)
      .repartition(2)
      .write
      .mode(SaveMode.Overwrite)
      .option("compression", "org.apache.hadoop.io.compress.GzipCodec")
      .option("spark.io.compression.codec", "snappy")
      .option("header", "true")
      .csv("test")

  }

}
