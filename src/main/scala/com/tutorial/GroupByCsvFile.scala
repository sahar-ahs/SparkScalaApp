package com.tutorial

import java.io.{File, PrintWriter}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lit, monotonically_increasing_id, row_number}
import org.apache.spark.{SparkConf, SparkContext}

/*
* Create by Sahar on 8/15/2020 10:05 PM.
*/

object GroupByCsvFile {
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
   val writer=new PrintWriter(new File("src/Resources/covTypeDs/data5.txt"))

    df1.filter(col("row_num").between(7999, 9999)).groupBy("KKK").count().show()
    df1.filter(col("row_num").between(37999, 39999)).groupBy("KKK").count().show()
    df1.filter(col("row_num").between(157999, 159999)).groupBy("KKK").count().show()
    df1.filter(col("row_num").between(317999, 319999)).groupBy("KKK").count().show()

  }
}
