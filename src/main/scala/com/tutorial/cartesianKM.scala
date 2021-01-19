package com.tutorial

import breeze.linalg.{DenseVector, squaredDistance}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

object cartesianKM {

  def distance(x: linalg.Vector, y: linalg.Vector):Double = {
    //squaredDistance(x,y)
   // math.pow((x-y),2)
    12.4
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("KMeansExfample").setMaster("local[*]")

    val sc = new SparkContext(conf)
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val data = sc.textFile("src/Resources/IrisNumeric.txt")
    val numClusters = 3
    val numIterations = 10
    //val clusters =new KMeans().setFeaturesCol("features")
    val parsedData = data.map(s => Vectors.dense(s.split(',').map(_.toDouble))).cache()
    val clusters = KMeans.train(parsedData, numClusters, numIterations)
   val clusterCenters= sc.parallelize(clusters.clusterCenters)
    val result1 = parsedData.cartesian(clusterCenters).map {
      case (x,y) => ((x,y),distance(x,y))
    }

    val result = parsedData.cartesian(clusterCenters)//.map{
   // result.foreach(x=>x.)
      //case ((a,b),(c,d))=>((a,c),math.pow((b-d),2))
  //  }
    result1.foreach(x=>println(x))
  }
}
