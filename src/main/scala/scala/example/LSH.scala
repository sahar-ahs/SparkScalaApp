package scala.example

import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.feature.BucketedRandomProjectionLSH
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.linalg.DenseVector
import org.apache.spark.sql.{Column, Dataset, Row, SparkSession}
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.types.{ArrayType, DoubleType, IntegerType, StructField, StructType}
import org.apache.spark.ml.linalg.SQLDataTypes.VectorType
import breeze.linalg.{ Vector}
import scala.collection.mutable.ArrayBuffer

object LSH {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .appName("Spark SQL basic example")
      .master("local[*]")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    /*val dfA = spark.createDataFrame(Seq(
      (0, Vectors.dense(1.0, 2.0)),
      (2, Vectors.dense(0.6, 1.1)),
      (10, Vectors.dense(0.0, 0.1)),
      (12, Vectors.dense(0.0, 0.1)),
      (13, Vectors.dense(0.0, 0.1))


    )).toDF("id", "features")*/


    val dfA = Seq(
      Row(1, Vectors.dense(1, 2)),
      Row(2, Vectors.dense(1, 2)),
      Row(3, Vectors.dense(5, 2.5)),
      Row(4, Vectors.dense(1, 2)),
      Row(5, Vectors.dense(1, 2)),
      Row(6, Vectors.dense(1, 2)),
      Row(7, Vectors.dense(21, 7.4)),
      Row(8, Vectors.dense(3.6, 2)),
      Row(9, Vectors.dense(12, 7.9)),
      Row(10, Vectors.dense(14.9, 38.8)),
      Row(11, Vectors.dense(12.9, 5.8)),
      Row(12, Vectors.dense(5.8, 2.2)),
      Row(13, Vectors.dense(21, 2.89)),
      Row(14, Vectors.dense(34.9, 123)),
      Row(15, Vectors.dense(234, 564)),
      Row(16, Vectors.dense(12.9, 87.5)),
      Row(17, Vectors.dense(1.12, 2.43)),
      Row(18, Vectors.dense(321, 2.453)),
      Row(19, Vectors.dense(123, 550)),
      Row(20, Vectors.dense(1.122, 1232.33)),
      Row(21, Vectors.dense(1, 2)),
      Row(22, Vectors.dense(1, 2)),
      Row(23, Vectors.dense(1.33, 2)),
      Row(24, Vectors.dense(1, 2)),
      Row(25, Vectors.dense(1.56, 22.2))

    )
    val schema1 = StructType(
      Seq(
        StructField("id", IntegerType, true),
        StructField("features", org.apache.spark.ml.linalg.SQLDataTypes.VectorType, true)
      ))

    val df1 = spark.createDataFrame(spark.sparkContext.parallelize(dfA), schema1)
df1.show()
    val dfB = spark.createDataFrame(Seq(
      (4, Vectors.dense(1.1, 0.4)),
      (5, Vectors.dense(2.0, 0.5)),
      (6, Vectors.dense(0.6, 3.8)),
      (2, Vectors.dense(1.1, 1.2))
    )).toDF("id", "features")


    val brp = new BucketedRandomProjectionLSH()
      .setBucketLength(5)
      .setNumHashTables(3)
      .setInputCol("features")
      .setOutputCol("hashes")

    val model = brp.fit(df1)

    // Feature Transformation
    println("The hashed dataset where hashed values are stored in the column 'hashes':")
    model.transform(dfB).show()

    println("Approximately joining dfA and dfB on Euclidean distance smaller than 1.5:")
  val result= model.approxSimilarityJoin(df1, dfB, 30000, "EuclideanDistance")
      .select(col("datasetA.id"),col("datasetA.features").alias("idA"),
        col("datasetB.id").alias("idB"),
        col("EuclideanDistance")).orderBy("EuclideanDistance").dropDuplicates("idA")//.show(100)
   val uu= result.rdd.map(x=>{
     (x.getInt(2),x.getAs[DenseVector](1).toArray)
    })

    uu.foreach(x=>{
      val io=breeze.linalg.Vector(x._2)

      println(io)
    })
   // val ii=oo.collectAsList()




   /* println("Approximately searching dfA for 2 nearest neighbors of the key:")
    val key = Vectors.dense(1.1, 0.4)
    val tt = model.approxNearestNeighbors(dfA, key, 1)
    tt.show()
    val dataTest = Seq(
      Row(1, Vectors.dense(1, 2)),
      Row(2, Vectors.dense(1, 2))
    )
    val schema = StructType(
      Seq(
        StructField("id", IntegerType, true),
        StructField("point", org.apache.spark.ml.linalg.SQLDataTypes.VectorType, true)
      ))
    val df = spark.createDataFrame(spark.sparkContext.parallelize(dataTest), schema)
    df.show()
    //dataDF.printSchema()
    df.printSchema()

    def myMethod(vector: Int): Int = {
      val tt = model.approxNearestNeighbors(dfA, Vectors.dense(1, 0.0), 1)
        .collectAsList().get(0).asInstanceOf[Row].getInt(0)
      tt
    }

    //val myUdf = udf(myMethod _)


    val upperUDF = udf((i: Int) => {
      val tt = model.approxNearestNeighbors(dfA, Vectors.dense(1, 2.0), 1)
        .collectAsList().get(0).asInstanceOf[Row].getInt(0)
      tt
    }).asNondeterministic

    val myFunction: Function1[Int, Int] = (vector: Int) => {
      // complex logic that returns a Double
      val tt = model.approxNearestNeighbors(dfA, Vectors.dense(1, 2.0), 1)
        .collectAsList().get(0).asInstanceOf[Row].getInt(0)
      tt
    }

    val myUdf = udf(myFunction)

    def removeAllWhitespace(col: Column): Column = {
      val c = col(12)
      c

    }
    /* def yearDiff(end: Column, start: Column): Column = {
       Datediff(end, start)/365
     }*/
    //----------------------------------------------------------------------------------------------
    df.withColumn("nearest", upperUDF(col("id"))).show(200)

    // val ff= res.collect()//.foreach(x=> println(x.select(col("id"))))
    //println(ff.foreach(x=>println(x)))*/

  }
}
