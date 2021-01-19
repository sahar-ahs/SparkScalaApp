package scala.example

import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object dataFrame {
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

    val simpleData = Seq(Row(Vectors.dense(1,2)),
      Row(Vectors.dense(1,2),
        Row(Vectors.dense(1,2)
        )
    ))

    val simpleSchema = StructType(Array(
      StructField("point",org.apache.spark.ml.linalg.SQLDataTypes.VectorType,true)
    ))
    val df = spark.createDataFrame(
      spark.sparkContext.parallelize(simpleData),simpleSchema)
    df.printSchema()
    df.show()
  }
}
