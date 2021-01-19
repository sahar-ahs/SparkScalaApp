package scala.example

import java.time.{Duration, Instant}
import java.util

import breeze.numerics.pow
import org.apache.orc.impl.TreeReaderFactory.StructTreeReader
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.DenseVector
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object SortingArray {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("KMeansExample").setMaster("local[*]")
    val spark=SparkSession
      .builder()
      .appName("SAS")
      .master("local[*]")
      .config(conf)
      .getOrCreate()
    //val sc = new SparkContext(conf)
   val dataRdd= spark.sparkContext.textFile("src/Resources/kddcup44.csv")
   val ff= spark.sparkContext.parallelize(Array(1,2,4,5,6,8,7,9,10))


    val start=Instant.now
    val iterator= dataRdd.toLocalIterator
    iterator.slice(0,10).foreach(x=>println(x))
    val end=Instant.now()
    println("itarator time: "+Duration.between(start,end).toMillis + " ms")

    val start1=Instant.now
     dataRdd.foreachPartition(x=>{
       x.slice(0,10).foreach(x=>println(x))
    })
   // iterator1.slice(0,10).foreach(x=>println(x))
    val end1=Instant.now()
    println("collect time: "+Duration.between(start1,end1).toMillis + " ms")

     // .mapValues(_.map(_.toInt).sorted)
  //  ff.sortBy(x=>x._2)(Ordering.by(Vector).reverse).collect().foreach(x=>println(x))





      //testData.sortBy(event => event._1.values)(Ordering.by(e=>e))
/*
    val mcInfo: Array[(breeze.linalg.Vector[Double], Int)] = Array(
      (Vectors.dense(1.9, 11.6), 9),
      (Vectors.dense(6.3, 12.9), 12),
      (Vectors.dense(1.3, 12.6), 1))*/
//mcInfo.sortBy(x=>x._1)(Ordering.by(breeze.linalg.Vector[Double]).reverse)
/*    val secondData = mcInfo.map(x => {
      ( Vectors.dense(x._1.toArray),x._2)
    })
    var secondDf = spark.createDataFrame(secondData).toDF("id", "features").cache()
    secondDf.printSchema()
val datasetDf=secondDf.repartition(1).sort("features")
val rddData=datasetDf.rdd.map(a=>{
  (a.getInt(2),a.getAs[DenseVector](1))
})*/

    //  mcInfo.toArray.foreach(x=>x._1.asInstanceOf[Tuple1])
   // data.takeOrdered(2)(Ordering.by(x=>x).foreach(x=>println(x))
    // mcInfo.sorted.foreach(x=>println(x._1))
    //mcInfo.sortBy{ case (k, v) => v}//.foreach(x=>println(x))
    // val l = List(A(Vector(8.9,50),1),A(Vector(7.9,2),2),A(Vector(1.9,2),3))
    //mcInfo.foreach(x=>println(x._1.sortBy(s=>s)))

    //l.sortBy(x=>x.tag).foreach(x=>println(x))

  }


    /*case class A(tag:Array[Double]) extends Ordered[A] {
      // Required as of Scala 2.11 for reasons unknown - the companion to Ordered
      // should already be in implicit scope
      import scala.math.Ordered.orderingToOrdered

      def compare(that: A):Array[Double] = (this.tag zip that.tag).map { case (point, center) => point-center}
    }*/
}
