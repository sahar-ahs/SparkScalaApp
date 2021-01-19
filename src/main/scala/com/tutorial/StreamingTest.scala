package com.tutorial

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

object StreamingTest {
  def main(Args:Array[String]): Unit ={
    val conf=new SparkConf().setAppName("streamTest")
      .setMaster("local[2]").set("spark.ui.enabled", "True").set("spark.ui.port","4040")
    val sc=new SparkContext(conf)
    val batchInterval = 10
    val ssc=new StreamingContext(sc,Seconds(batchInterval))
    val lines= ssc.socketTextStream("localhost",9999,StorageLevel.MEMORY_ONLY)
   // ssc.checkpoint("src/Resources/output")
    ssc.checkpoint("checkpoint")
//--------------read from file--------------
 // val l1=  ssc.textFileStream("src/Resources/input.txt")
  // l1 flatMap { _.split(" ")}
    lines filter{_.length>0}
   // lines print()
  // val l11= lines.flatMap(_.split(" ")).map(w=>(w,1)).reduceByKey((a,b)=> if(a>b)a else b)
   // l11.print()
//-------count by value----------
 // val word=  lines flatMap { _.split(" ")}
  //  word.countByValue().print()
//---------- SORT BY ---------------
    // بعضی دستورات مخصوص rdd هست و نمیشه اینجا زد به همین خاطر از transform استفاده می کنیم
//val words = lines.flatMap{line => line.split(" ")}
  //  val sorted = words.transform{_.sortBy(w=>w,true)}
   // sorted.print()
//---------------update state by key--------------------
   // val words = lines.flatMap{line => line.split(" ")}
   // val wordPairs = words.map{word => (word, 1)}
    // create a function of type (xs: Seq[Int], prevState: Option[Int]) => Option[Int]
    val updateState = (xs: Seq[Int], prevState: Option[Int]) => {
      prevState match {
        case Some(prevCount) => Some(prevCount + xs.sum)
        case None => Some(xs.sum)
      }
    }
 //   val runningCount = wordPairs.updateStateByKey((updateState))
  //  runningCount.print()
//---------------- foreach rdd -------------------
    // chizi bar nmegardanad
 // val w=  lines.foreachRDD(rdd=>rdd.foreach(x=>x))

//-------------------------------------
  //  val wordCount=lines.flatMap(_.split("")).map(word=>(word,1)).reduceByKey(_+_)
 /* val words=lines.flatMap(_.split(" "))
val pair=  words.map(word=>(word,1))
val wordCount=  pair.reduceByKey(_+_)*/
// println(wordCount)

    //wordCount.print()
  //  wordCount.saveAsTextFiles("src/Resources/output")
   // wordCount.toDebugString()
//--------------------------------
    //lines.transform(x=>x.RDD)
  val arr = new ArrayBuffer[String]();
 // val wow=lines.flatMap(x=>x.split(",")).asInstanceOf[testClass]
   val wow=lines.map(x=> People1().convertLine(x.toString())).filter(people=>people.studentId>2)
    wow.print()
/*val wow1=lines.flatMap(x=>x.split(","))
w1.foreachRDD{rdd=>
 arr++=rdd.collect()
}*/
//  arr.foreach(x=>println(x))
//  val sds=  wordss.asInstanceOf[People1]

ssc.start()
ssc.awaitTermination()

}

}
//case class KafkaPayload(value: Array[Byte])
case class testClass(value:DStream[String])
case class People1() {

var studentId:Int=0
var examCenterId  :Int=0
var subject  :String=""
var year  :String=""
var quarter  :String=""
var score :String=""
var grade  :String=""

/*def this(){
this()  // سازنده کلاس پدر که اگر ورودی نداشته باشد خب اینجا هم چیزی نمیگیرد اما اگر ورودی داشته باشد پدر، اینجا هم باید یدی یا کوتشن خالی بگذاری
}*/

def this(studentId:Int,examCenterId:Int,subject:String="",year:String="",quarter:String="",score:String="",grade:String="")={
this()
this.studentId=studentId
this.examCenterId=examCenterId
this.subject=subject
this.year=year
this.quarter=quarter
this.score=score
this.grade=grade
// println(this.toString())

}
def convertLine(word: String): People1={
/* word.foreachRDD((rdd)=>{

 rdd.map(w=>new People1(w(0).toInt,w(1).toInt,w(2).toString,w(3).toString,w(4).toString,w(5).toString,w(6).toString) )
})
val arr=word.map(w=>new People1(w(0).toInt,w(1).toInt,w(2).toString,w(3).toString,w(4).toString,w(5).toString,w(6).toString))*/
val arr:Array[String]=word.split(",").map(_.trim)
new People1(arr(0).toInt,arr(1).toInt,arr(2),arr(3),arr(4),arr(5),arr(6))
//  arr.asInstanceOf[People1]
//   new People(arr(0).toInt,arr(1).toInt,arr(2),arr(3),arr(4),arr(5),arr(6))
}
override def toString:String = {

(s"people is{stid=$studentId examid=$examCenterId subject=$subject year=$year qu=$quarter sc=$score gr=$grade }")
}
}
