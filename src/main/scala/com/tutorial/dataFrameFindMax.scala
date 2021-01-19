package com.tutorial

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, max}
import org.apache.spark.sql.types.DataTypes

object dataFrameFindMax {
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

    val data = spark.read.format("csv").option("sep", ",").option("header", true)
      .load("src/Resources/testtt.csv")

    var df = data
    df = df.select(col("A").cast(DataTypes.IntegerType),
      col("B").cast(DataTypes.IntegerType),
      col("C").cast(DataTypes.IntegerType),
      col("D").cast(DataTypes.IntegerType),
      col("E").cast(DataTypes.IntegerType),
      col("F").cast(DataTypes.IntegerType),
      col("G").cast(DataTypes.StringType))
    //df.printSchema()
    // val ff=  df.agg(max("A")).head().getInt(0)
    val df2 = df.na.fill(df.agg(max("A")).head().getInt(0), Seq("A"))
      .na.fill(df.agg(max("B")).head().getInt(0), Array("B"))
      .na.fill(df.agg(max("C")).head().getInt(0), Array("C"))
     .na.fill(df.agg(max("D")).head().getInt(0), Array("D"))
      .na.fill(df.agg(max("E")).head().getInt(0), Array("E"))
      .na.fill(df.agg(max("F")).head().getInt(0), Array("F"))


    df2.show()

  }
}
