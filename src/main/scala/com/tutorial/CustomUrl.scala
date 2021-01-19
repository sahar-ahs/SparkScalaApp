package com.tutorial

import java.io.{BufferedReader, InputStreamReader}
import java.net.{URL, URLConnection}
import java.time.Instant

import org.apache.log4j.{Level, Logger}
import org.apache.spark.internal.Logging
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object CustomUrl {
  import java.time.Duration
  def timer[R](block: => R): R = {
    val t0 =System.nanoTime()// Instant.now() //
    val result = block // call-by-name
    val t1 =System.nanoTime()//Instant.now()
    println(s"Elapsed time: " + (t1 - t0) / 1000000 + "ms")
   //println(s"Elapsed time: " + Duration.between(t0 ,t1) + "ms")

    result
  }
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Spark CluStream12").setMaster("local[*]")
      //  .set("spark.streaming.backpressure.enabled","true")
      //.set("spark.streaming.backpressure.initialRate","2000")
      .set("spark.streaming.kafka.maxRatePerPartition","200")
      .set("spark.ui.enabled", "True")
      .set("spark.ui.port", "4040")
    //    val conf = new SparkConf().setAppName("Stream Word Count").setMaster("spark://192.168.0.119:7077")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    // val ssc = new StreamingContext(sc, Milliseconds(1000))
    val ssc = new StreamingContext(sc,Milliseconds(1000))
  //  val lines = ssc.socketTextStream("localhost", 9998)
    val lines = ssc.receiverStream(new UrlReceiver("http://stream.meetup.com/2/rsvps"))
   lines.print()

    ssc.start()
    ssc.awaitTermination()
      /*val urlConnection: URLConnection = new URL("http://stream.meetup.com/2/rsvps").openConnection
      val bufferedReader: BufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream))
      var msg = bufferedReader.readLine
      while (msg != null) {
        if (!msg.isEmpty) {
          timer {
            println(msg)
          }
        }
        msg = bufferedReader.readLine
      }*/
    }

}
class UrlReceiver(urlStr: String) extends Receiver[String](StorageLevel.MEMORY_AND_DISK_2) with Logging {

  override def onStart() = {
    new Thread("Url Receiver") {
      override def run() = {
        val urlConnection: URLConnection = new URL(urlStr).openConnection
        val bufferedReader: BufferedReader = new BufferedReader(
          new InputStreamReader(urlConnection.getInputStream)
        )
        var msg = bufferedReader.readLine
        while (msg != null) {
          if (!msg.isEmpty) {
            store(msg)

          }
          msg = bufferedReader.readLine
        }
        bufferedReader.close()
      }
    }.start()
  }

  override def onStop() = {
    // nothing to do
  }
}