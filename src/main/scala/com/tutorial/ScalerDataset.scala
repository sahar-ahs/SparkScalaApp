package com.tutorial

import java.io.{FileWriter, IOException}

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.feature.{StandardScaler, StandardScalerModel}
import org.apache.spark.mllib.linalg.Vectors

object ScalerDataset {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("StandardScalerEx").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // $example on$
    val data=sc.textFile("src/Resources/covtype.csv").map(_.split(",").map(_.toDouble)).map(x=> Vectors.dense(x))
    val scaler1 = new StandardScaler().fit(data)
   // val scaler2 = new StandardScaler(withMean = true, withStd = true).fit(data)

    // data1 will be unit variance.
    val data1 = data.map(x => (scaler1.transform(x)))

    // data2 will be unit variance and zero mean.
    //val data2 = data.map(x =>scaler1.transform(x))
    // $example off$

    println("data1: ")
    data1.take(5).foreach(x=>println(x))
    println("data2: ")
    //data2.foreach(x => println(x))
   // data2.take(5).foreach(x=>println(x.toArray.mkString(",")))//.take(5)//.foreach(x=>println(x))
val ff=data1.repartition(1).collect().map(x=>x.toArray.mkString(","))
   // data2.map(x=>x.toArray.mkString(",")).repartition(5).saveAsTextFile("src/Resources/resultData")

    try{
      val fw=new FileWriter("src/Resources/scaledCovtype.csv")
      ff.foreach(x=> fw.write(x +"\n"))
      fw.close()
    }
    catch {
      case e: IOException => println("Had an IOException trying to read that file")
    }
    println("Success...")

  sc.stop()
  }
}
