package com.tutorial

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.monotonically_increasing_id

object RandomGenerator {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[*]")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    //  val conf = new SparkConf().setAppName("StandardScalerEx").setMaster("local[*]")
    // val sc = new SparkContext(conf)
    val arr:Array[String] =new Array[String](6)
    def randomStringGen(): String = {

      arr(0)=scala.util.Random.nextInt(15) + 1.toString
      arr(1)=scala.util.Random.nextInt(10) + 1.toString
      arr(2)=scala.util.Random.nextInt(8) + 1.toString
      arr(3)=scala.util.Random.nextInt(12) + 1.toString
      arr(4)=scala.util.Random.nextInt(4) + 1.toString
      arr(5)="null"

      val rnd = scala.util.Random.nextInt(5) + 1
      val uu = arr(rnd)
      uu
    }

    val rddData = spark.sparkContext.parallelize(Seq.fill(1000000) {
      (
        randomStringGen(),
        randomStringGen(),
        randomStringGen(),
          randomStringGen(),
        randomStringGen(),
        randomStringGen()

      )
    }, 1) //.toDF("col_1", "col_2", "col_3")
    var df = spark.createDataFrame(rddData)
    val df1=df.repartition(1).withColumn("customerNumber",monotonically_increasing_id())

    df1.write.csv("src/Resources/GenerateRndData.csv")

  }
}
