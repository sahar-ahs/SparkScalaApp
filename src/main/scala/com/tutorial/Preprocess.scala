package com.tutorial

import java.io.{File, PrintWriter}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object Preprocess {
  def main(args: Array[String]): Unit = {
    val spark=SparkSession.builder().appName("abc").master("local[*]").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    val data=spark.read.format("csv").option("header",false).load("src/Resources/kddcupComplete.csv")
    val df1 = data.toDF("A", "B", "C", "D", "E", "F", "G", "H", "V", "Q", "QQ",
      "QQQ", "ER", "DS", "QA", "GH", "FF", "AW", "JU", "CV",
      "P", "L", "UU", "VB", "JK", "BN", "NM", "MMC", "RFC", "ASD", "TY", "IU", "OP", "lk","lgk", "KKK")
   val dfData= df1.repartition(1).drop("KKK").collect()

    val writer = new PrintWriter(new File("src/Resources/kddcupEdited.csv"))
    dfData.foreach(x=>{
      val d=x.toString().replace("[","").replace("]","")
      writer.write(d+ "\n")

    })
    writer.close()
  }
}
