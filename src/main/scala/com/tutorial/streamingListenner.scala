package com.tutorial

import breeze.linalg.DenseVector
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.scheduler.{StreamingListener, StreamingListenerBatchCompleted}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object streamingListenner {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("streamTest")
      .setMaster("local[2]").set("spark.ui.enabled", "True").set("spark.ui.port", "4040")

    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    val batchInterval = 10
    val ssc = new StreamingContext(sc, Seconds(2))
    val lines = ssc.socketTextStream("localhost", 9999, StorageLevel.MEMORY_ONLY)
    val model=new WriteData()
    ssc.addStreamingListener(new PrintClustersListener(sc,model))
    model.run(lines.map(x=>x.split(",").map(_.toDouble)).map(DenseVector(_)))
   // new WriteData(lines.map(x=>x.split(",").map(_.toDouble)).map(DenseVector(_)))


    ssc.start()
    ssc.awaitTermination()

  }
}
  class PrintClustersListener( sc: SparkContext,model:WriteData) extends StreamingListener {

    override def onBatchCompleted(batchCompleted: StreamingListenerBatchCompleted) {
      if (batchCompleted.batchInfo.numRecords > 0) {
        println(batchCompleted.batchInfo.numRecords)
        println("tc= "+model.getCurrentTime +"n= "+model.getTotalPoints)
      }

    }
}



