package com.tutorial

import java.time.{Duration, Instant}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/*
* Create by Sahar on 8/9/2020 5:18 AM.
*/

object master_slave_test extends App{
  val conf = new SparkConf().setAppName("example")//.setMaster("local[*]")
    .setMaster("spark://172.22.3.206:7077")
  //.set("deploy-mode","client")
  val sc = new SparkContext(conf)
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  val start=Instant.now
 val data= sc.parallelize(Seq(1,2,3,4,5,6,7,8,9,10))
  println(data.sum())
  println(data.count())
  val end=Instant.now
  val durationStep=Duration.between(start, end).toMillis
  println(durationStep)
}
