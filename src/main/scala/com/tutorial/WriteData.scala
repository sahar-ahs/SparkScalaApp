package com.tutorial

import breeze.linalg.DenseVector


import org.apache.spark.streaming.dstream.DStream

class WriteData()  extends Serializable {
  private var time: Long = 0L
  private var N: Long = 0L
  private var currentN: Long = 0L
  def getTotalPoints: Long = {
    this.N
  }
  def getCurrentTime: Long = {
    this.time
  }
    def run(data:DStream[breeze.linalg.Vector[Double]]): Unit ={
     data.foreachRDD{rdd=>
       currentN= rdd.count()
       if(currentN!=0)
         println("aaaa")

       this.N+=currentN
       this.time+=1
     }

  }
}