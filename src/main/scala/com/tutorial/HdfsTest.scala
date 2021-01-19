package com.tutorial

import org.apache.spark.{SparkConf, SparkContext}

/*
* Create by Sahar on 7/19/2020 3:31 AM.
*/

object  HdfsTest extends App{
  val conf = new SparkConf().setAppName("RDD Programming Guide")
    .setMaster("local[*]")
    .set("spark.ui.enabled", "True")
    .set("spark.ui.port", "4040")
  val sc = new SparkContext(conf)
  val ll=sc.textFile("hdfs://localhost:9000/datadir/IrisNumeric.txt")
  ll.foreach(x=>println(x))
}
