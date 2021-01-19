package com.tutorial

import org.apache.spark.mllib.feature.{StandardScaler, StandardScalerModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.util.MLUtils

object StandardScalerSample {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("StandardScalerExample").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // $example on$
    //val data = MLUtils.loadLibSVMFile(sc, "src/Resources/sample_libsvm_data.txt")
val data=sc.textFile("src/Resources/IrisNumeric.txt").map(_.split(",").map(_.toDouble)).map(x=> Vectors.dense(x))
    val scaler1 = new StandardScaler().fit(data)
    val scaler2 = new StandardScaler(withMean = true, withStd = true).fit(data)
    // scaler3 is an identical model to scaler2, and will produce identical transformations
    val scaler3 = new StandardScalerModel(scaler2.std, scaler2.mean)

    // data1 will be unit variance.
    val data1 = data.map(x => (scaler1.transform(x)))

    // data2 will be unit variance and zero mean.
    val data2 = data.map(x =>scaler2.transform(x))
    // $example off$

    println("data1: ")
   // data1.foreach(x => println(x))
data1.take(5).foreach(x=>println(x))
    println("data2: ")
    data2.foreach(x => println(x))

    sc.stop()
  }
}
