package com.tutorial

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

object mySTRtest {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("streamTest")
      .setMaster("local[*]").set("spark.ui.enabled", "True").set("spark.ui.port","4040")
    val sc=new SparkContext(conf)
    val batchInterval = 10
    val ssc=new StreamingContext(sc,Seconds(1))
    val lines= ssc.socketTextStream("localhost",9999)
    val words=lines.flatMap(_.split(" "))
    val pair=  words.map(word=>(word,1))
    val wordCount=  pair.reduceByKey(_+_)
    wordCount.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
