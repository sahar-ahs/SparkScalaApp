package example.myclass

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object example1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ex").setMaster("local[*]")
      //.setMaster("spark://192.168.99.1:7077")
    //.set("deploy-mode","client")
    val sc = new SparkContext(conf)
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val data=sc.parallelize(Seq("spark","R","java","python","spark","program","R","R"))
   val wordCount= data.flatMap(_.split(",")).map(x=>(x,1)).reduceByKey(_+_)
    val badges_count_badge = wordCount.map({ case (x,y) => (y,x) })
   // badges_count_badge.take(1)

    val badges_sorted = badges_count_badge.sortByKey(false).map({ case (x, y) => (y, x) })
    badges_sorted.foreach(x=>println(x))
    badges_sorted.toDebugString
    wordCount.dependencies
  }
}
