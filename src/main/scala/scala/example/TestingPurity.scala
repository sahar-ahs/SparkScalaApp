package scala.example

import breeze.numerics.pow
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.linalg.{DenseVector, Vectors}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.{ArrayType, IntegerType, StringType, StructType}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object TestingPurity {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("sa")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    val schema = new StructType()
      .add("feature", StringType)
      .add("label", StringType)

    // centroids file
    def readFromFile(filePath: String): ArrayBuffer[String] = {
      val arr: ArrayBuffer[String] = ArrayBuffer()

      for (line <- Source.fromFile(filePath).getLines) {

        //val ln=  line.toVector
        arr.append(line)
      }
      arr

    }

    val centroidsArr = readFromFile("src/Resources/kddDs/centers/a/centers2")
    var df = spark.read.format("csv").option("sep", "-")
      .option("header", "false").schema(schema)
      .load("src/Resources/kddDs/dataLbl2.csv").toDF("feature", "label")

    val computeNearest = (strQuote: String) => {
      var minDist = Double.PositiveInfinity
      var minIndex = Int.MaxValue
      var i = 0
      var dd: Vector[Double] = Vector()
      val point = strQuote
        .replace("[","")
        .replace("]","")
        .split(",").map(_.toDouble)
      val clusterArr = centroidsArr.toArray.map(x => x.split(",").map(x => x.toDouble).toVector)
      clusterArr.slice(15,20).foreach(center => {

        val dist = (point zip center).map { case (point, center) => pow(point - center, 2) }.sum
       val equDistance= scala.math.sqrt(dist)
        if (equDistance < minDist) {
          minDist = dist
          minIndex = i
          dd = center
        }
        i = i + 1
      })
      minIndex
    }
    val getNearestClusterNumUdf = udf(computeNearest)
    import org.apache.spark.sql.functions._
    df = df.withColumn("clusterNumber", getNearestClusterNumUdf(col("feature")))
    val groupByIdAndLabelDf = df.groupBy("clusterNumber", "label").count()
    val groupByIdDf = df.groupBy("clusterNumber").count()
    val dfMaxCount = groupByIdAndLabelDf.groupBy("clusterNumber").agg(
      max("count").as("maxCount")
    )
   val joinMaxCountDf= groupByIdDf.join(dfMaxCount,groupByIdDf("clusterNumber")===dfMaxCount("clusterNumber"),"inner")
     .withColumn("divideMaxAndCount",col("maxCount").divide(col("count")))
     .drop(dfMaxCount("clusterNumber"))

    val sumColumn:Double = joinMaxCountDf.agg(sum("divideMaxAndCount")).first().getDouble(0)

  //  df.show()
    groupByIdAndLabelDf.show()
   // groupByIdDf.show()
   // dfMaxCount.show()
    joinMaxCountDf.show()
    println(s"sum maxCount: ${sumColumn}")
    println(s"total count: ${df.count()}")
    val purity:Double = (sumColumn.toDouble / 5) * (100/100)
    val purityRes="%.5f".format(purity).toDouble
    println(s"purity result: ${purityRes}")
  }
}
