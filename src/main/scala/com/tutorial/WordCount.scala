package com.tutorial

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
def main(Args:Array[String]){
  val conf=new SparkConf().setAppName("word count")//.setMaster("local[*]")
    .setMaster("spark://192.168.99.1:7077")
    .set("deploy-mode","cluster")
  val sc=new SparkContext(conf)
  val lines=sc.textFile("src/Resources/input.txt")
 // val counts=line.flatMap(_.split("")).map(word=>(word,1)).reduceByKey(_+_)
 val words=lines.flatMap(_.split(" "))
  val pair=  words.map(word=>(word,1))
  val wordCount=  pair.reduceByKey(_+_)
wordCount.foreach(x=>{
  println(x)
})
 // wordCount.saveAsTextFile("src/Resources/output")
 // counts.toDebugString()
 /* counts.persist()
  counts.cache()
  counts.unpersist()*/


}
}
