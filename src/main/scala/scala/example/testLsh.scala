package scala.example

import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.feature.{BucketedRandomProjectionLSH, VectorAssembler}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.functions.col

object testLsh {
  def main(args: Array[String]): Unit = {
    // contructing the dataframe
    val data= """1   11.6133  48.1075
2   11.6142  48.1066
3   11.6108  48.1061
4   11.6207  48.1192
5   11.6221  48.1223
6   11.5969  48.1276
7   11.5995  48.1258
8   11.6127  48.1066
9   11.6430  48.1275
10  11.6368  48.1278
11  11.5930  48.1156"""
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[*]")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    val df = spark.createDataFrame(Seq(
      (0, Vectors.dense(0.0, 1.0)),
      (1, Vectors.dense(1.3, 1.4)),
      (2, Vectors.dense(0.6, 6.1)),
      (3, Vectors.dense(0.0, 0.1))
    )).toDF("id", "X")

    val assembler = new VectorAssembler()
      .setInputCols(Array("X"))
      .setOutputCol("v")
    val df2 = assembler.transform(df)
    val lsh = new BucketedRandomProjectionLSH()
      .setInputCol("v")
      .setBucketLength(1e-3) // change that according to your use case
      .setOutputCol("lsh")
    val result = lsh.fit(df2).transform(df2).orderBy("lsh")

    // the lsh is in an array of vectors. To extract the double, we can use
    // getItem for the array and a UDF for the vector.
    val extract = udf((vector : org.apache.spark.ml.linalg.Vector) => vector(0))
    result.withColumn("lsh", extract(col("lsh").getItem(0))).show(false)
  }
}
