package com.tutorial

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkConf, SparkContext}

object SparkTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RDD Programming Guide")
      .setMaster("local[*]")
      .set("spark.ui.enabled", "True")
      .set("spark.ui.port", "4040");
    val sc = new SparkContext(conf)
    val broadcastVariable = "saaharBroad"
    sc.broadcast(broadcastVariable)
    val acc = sc.accumulator(0)
    // val res=  sc.parallelize(Array(1,2,3,4)).foreach(x=>acc+=x)
    val dd = Array(1, 2, 3, 4).foreach(x => acc += x)
    println(acc.value)
    // sc.wait(10000)

    //-------------mllib-------------------
    Vectors.dense(1.2, 2.1, 12.2)
    Vectors.sparse(10, Array(1, 3), Array(1.1, 2.0))
    val positive = LabeledPoint(1.0, Vectors.dense(10.0, 30.0, 20.0))
    val negetive = LabeledPoint(0.0, Vectors.dense(1, 2, 3))

      sc.stop()
  }

}
