package com.tutorial

import org.apache.log4j.Level
import org.apache.log4j.Logger
import java.io.{File, FileReader, FileWriter, PrintWriter}

import org.apache.hadoop.classification.InterfaceAudience.Private
import org.apache.log4j.Level

import scala.collection.mutable.ListBuffer
import scala.io.Source

object FileReaderTest {
def  main(Args:Array[String]): Unit ={

 Logger.getLogger("org.apache").setLevel(Level.WARN)
/*  try {
   //java type
  val f = new FileReader("src/Resources/input.txt")
   print (f.read())
 }
 catch{
   case ex:Exception =>{
     print("فایل مورد نطر یافت نشد!")
   }
 }*/
 // scala type
 val home=System.getProperty("user.home")
 //val s=Source.fromFile(s"${home}/input.txt").foreach{ print }
// Source.fromFile("src/Resources/input.txt").foreach{ print }
//val ss=  Source.fromFile("src/Resources/input.txt").getLines().take(4)
 //val ss=Source.fromFile(s"${home}/input.txt").getLines().take(1)
 //print(ss)
//---------
//val sqs=Source.fromFile("src/Resources/students.csv").getLines().drop(1).take(5).asInstanceOf[People]
 //println(sqs)
var fruits = new ListBuffer[List[String]]()
 for (lines<- Source.fromFile("src/Resources/people.csv").getLines().drop(1).take(2))
      {
    //  println(lines.split(",").map(_.trim))
     val jj=  MyConvertor(lines)
        println(jj)
      //  println(jj.toString())

        val ss=People().convertLine(lines)

      // println(ss)


    //  println(s"$student_id $exam_center_id $subject $year $quarter $score $grade")
 }

 def MyConvertor(line:String):List[String] ={
  val q=line.split(",").map(_.trim)
 val l= List(q(0),q(1),q(2),q(3),q(4),q(5),q(6))
  // ListBuffer[List[String]]=l
 // fruits=l
//fruits.flatten
   l

 }
 // write in file
  /*try {
    val writer = new PrintWriter(new File("src/Resources/input.txt"))
    writer.write("Scala Program Language!")
    writer.close()
  }
  catch {
    case ex:Exception=>{
      print("فایل مورد نطر یافت نشد!")
    }
  }*/
// append
 val ww=new FileWriter("src/Resources/input.txt",true)
 //ww.write("\n Salam Sahar")
//  println("write done!")
 ww.close()
}
}

 case class People(){

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
 def convertLine(line:String): People ={
  val arr:Array[String]=line.split(",").map(_.trim)
  new People(arr(0).toInt,arr(1).toInt,arr(2),arr(3),arr(4),arr(5),arr(6))
 }
 override def toString:String = {

   (s"people is{stid=$studentId examid=$examCenterId subject=$subject year=$year qu=$quarter sc=$score gr=$grade }")
 }
}
