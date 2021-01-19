package com.tutorial

import org.apache.spark.{SparkConf, SparkContext}

object simple_kmean {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
    import org.apache.spark.mllib.linalg.Vectors
    val conf = new SparkConf().setAppName("KMeansExample").setMaster("local[*]")
    //.setMaster("spark://192.168.99.1:7077")
    //.set("deploy-mode","client")
    val sc = new SparkContext(conf)
    // Load and parse the data
    val data = sc.textFile("src/Resources/IrisNumeric.txt")
    val parsedData = data.map(s => Vectors.dense(s.split(',').map(_.toDouble))).cache()

    // Cluster the data into two classes using KMeans
    val numClusters = 3
    val numIterations = 20
    val clusters = KMeans.train(parsedData, numClusters, numIterations)

    // Evaluate clustering by computing Within Set Sum of Squared Errors
    val WSSSE = clusters.computeCost(parsedData)
    println("Within Set Sum of Squared Errors = " + WSSSE)

    // Save and load model
    clusters.clusterCenters.foreach(x=>println(x))
   /* clusters.save(sc, "target/org/apache/spark/KMeansExample/KMeansModel")
    val sameModel = KMeansModel.load(sc, "target/org/apache/spark/KMeansExample/KMeansModel")
*/
  }
}
