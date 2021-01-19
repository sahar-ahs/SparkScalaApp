package com.tutorial

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, monotonically_increasing_id}

/*
* Create by Sahar on 8/15/2020 10:05 PM.
*/

object partitionDataset {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[*]")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    val data = spark.read.format("csv").option("sep", ",").option("header", false)
      //.load("src/Resources/scaledCovtype.csv")
      .load("src/Resources/kddcupLabled.csv")

    val df1 = data.toDF("A", "B", "C", "D", "E", "F", "G", "H", "V", "Q", "QQ", "QQQ", "ER", "DS", "QA", "GH", "FF", "AW", "JU", "CV",
      "P", "L", "UU", "VB", "JK", "BN", "NM", "MMC", "RFC", "ASD", "TY", "IU", "OP", "lk", "KKK")
      .repartition(1).withColumn("row_num", monotonically_increasing_id())
    var start=0
    var end=1999
    for(i<- 1 until 248) {
  val dataset = df1.filter(col("row_num").between(start, end))
    .drop(col("row_num"))
  val dataDf = dataset.drop("KKK") //.take(2000)
  dataDf.write.csv(s"src/Resources/splitKddData/${i}.csv")
      start=end+1
      end=start+1999
}
    /*val writer = new PrintWriter(new File("src/Resources/kddDs/245.csv"))
    dataDf.foreach(x=>{
      writer.write(x.toString()+ "\n")
    })
    writer.close()*/

  }
}
