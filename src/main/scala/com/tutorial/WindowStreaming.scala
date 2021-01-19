package com.tutorial

import java.io.BufferedReader

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.internal.Logging

import scala.collection.mutable.Queue

object WindowStreaming extends App {
  val conf=new SparkConf().setAppName("streamTest")
    .setMaster("local[*]").set("spark.ui.enabled", "True").set("spark.ui.port","4040")
  val sc=new SparkContext(conf)
  setStreamingLogLevels()
  val ssc=new StreamingContext(sc,Seconds(10))

 // val lines = ssc.socketTextStream("localhost", 9999)
//val file=ssc.textFileStream("src/Resources/input.txt")
//  file.foreachRDD(x=>{
 //   x.flatMap(_.split(" ")).saveAsTextFile("src/Resources/tyt/g.txt")
//  })
  //val words = lines flatMap {line => line.split(" ")}
 // words.print()
  val windowLen = 30
  val slidingInterval = 10
 // val window = words.window(Seconds(windowLen), Seconds(slidingInterval))
 // val longestWord = window reduce { (word1, word2) =>
// if (word1.length > word2.length) word1 else word2 }
 // longestWord.print()
//------------------------------------------
 // ssc.checkpoint("checkpoint")
//val countByWindow = words.countByWindow(Seconds(windowLen), Seconds(slidingInterval))
 //countByWindow.print()

//---------------------------------------------
  //val countByValueAndWindow = words.countByValueAndWindow(Seconds(windowLen),
  //  Seconds(slidingInterval))
 // countByValueAndWindow.print()
//----------------------------------------------
 // ssc.checkpoint("checkpoint")
 // val numbers = words map {x => x.toInt}
 // val sumLast30Seconds = numbers.reduceByWindow(_+_,
  //  Seconds(windowLen), Seconds(slidingInterval))
  //sumLast30Seconds.print()
//-------------------------------------------------
 // ssc.checkpoint("checkpoint")
 // val wordPairs = words map {word => (word, 1)}
  //val wordCountLast30Seconds = wordPairs.reduceByKeyAndWindow(_+_, Seconds(windowLen), Seconds(slidingInterval))
 // wordCountLast30Seconds.print()
//------------------------------------------
 // ssc.checkpoint("checkpoint")
 // val wordPairs = words map {word => (word, 1)}
 // def add(x: Int, y: Int): Int = x + y
  //def subtract1(x: Int, y: Int): Int = x -y
 // val wordCountLast30Seconds = wordPairs.reduceByKeyAndWindow(add, Seconds(windowLen), Seconds(slidingInterval))
  //wordCountLast30Seconds.print()
  //--------------------------------------------------
  val users=List("hj","hgj")


 //val rddQueu= ssc.sparkContext.parallelize(RDD[Int])

  val inputQueu=new Queue[RDD[Int]]()
  val tnputStream=ssc.queueStream(inputQueu)
// val rddRes= tnputStream.map(x=>(x.split(" ")))
 val rddRes= tnputStream.map(x=>(x % 10,1))

  //rddRes.print()

  // Create and push some RDDs into
 /* for (i <- 1 to 30) {
    //var nums:Seq[Int] =scala.collection.mutable.Seq[Int](1,100)
    var nums:Seq[String] =scala.collection.mutable.Seq.apply("ghh")

    inputQueu += ssc.sparkContext.makeRDD(1 to 1000, 10)
    Thread.sleep(1000)
  }*/

  ssc.start()
  ssc.awaitTermination()

  def setStreamingLogLevels() {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
     // logInfo("Setting log level to [WARN] for streaming example." +
      //  " To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }
}
