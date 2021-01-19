package scala.example

import scala.annotation.tailrec
import scala.collection.JavaConverters._

object Example1 extends App{
val x=10
  val w={
    val x=5
    val y=20
    y+x
  }
  //println(x+w)
 // val doubler = (o:Int)=>o*2
// val doubler:Function1[Int,Int] = (o:Int)=>o*2
 // println(doubler(8))

  val doubler:Function2[Int,Int,Any] = (o:Int,v:Int)=>o*2
  val doubler1:(Int,Int)=>Int =_+_

// val mayRenge=Range(1,10).map(_.toString).mkString("")
   val mayRenge=Range(1,10)


 //println( mayRenge.filter(_ % 3 ==0).map(_.toString).mkString("\n"))
//  println((for(i<- mayRenge if i % 3!=0) yield i.toString).mkString("\n"))

 // fiboonachi

 /* def fib(n:Int)={
    var x1=1
    var x2=1
    for (i<-1 until  n){
      val _x1=x1
      val _x2=x2
      x1=_x2
      x2=_x1+_x2

    }
    x1

  }*/
 /* def fib(n:Int)={
   var x=Tuple2(1,1)
    for (i<-1 until  n){
      x=(x._2,x._1+x._2)
    }
    x._1

  }*/
/* def fib(n:Int,x: (Int,Int)=(1,1)):Int={
   if(n<=0)
    x._1
   else
   fib(n-1,Tuple2(x._2,x._1+x._2))

 }*/
  @tailrec
 def fib(n:Int,x: (Int,Int)=(1,1)):Int={
  n match {
    case 0 |1 =>x._1
    case m:Int if m>1 => fib(n-1,(x._2,x._1+x._2))
  }

 }
  for(i<-1 to 5){
   // println(fib(i))
  }
// ----lazy----
  var a=10
 lazy val b=a
  a=50
//  print (b)

  // ----
  val mylist=Seq(1,2,3)
 // println(mylist.filterNot(_%2==0))

  val t=mylist.map(x=>Seq(x)).flatten
//println(t)
  val t1=mylist.flatMap(x=>Seq(x))
  // fold left
  val folded=mylist.foldLeft(0)((res,element)=>res+element)
  //val folded=mylist.foldLeft(0)(_+_)
 // println(folded)
 val reduce=mylist.reduce((res1,res2)=> res1+res2)
 // val reduce=mylist.reduce(_+_)
//  println(reduce)
import scala.collection.JavaConverters._
 // val javaList=new util.ArrayList[String]()
 // javaList.add("sahar")
//  javaList.asScala
  mylist.asJava

//-----------------------------
val bag = List("1", "2", "foo", "8", "bar")
  var total=0
  for(i<- 0 until  bag.length){
 val resToInt= toInt(bag(i))
    if (resToInt !=None) {
     // total = total + bag(i).toInt
     // println(total)
    }
  }

 bag.foreach(x=> {
    val resToInt = toInt(x)
    resToInt match {
      case None =>println("not")
      case _ =>println(resToInt)
    }

  })
 // println(total)

  val myBagList=bag.flatMap(toInt(_)).reduce(_+_)  // .sum
  //println(myBagList)

  def toInt(x:String):Option[Int]= {
    try {
      Some(Integer.parseInt(x.trim))
    }
    catch {
      case e: NumberFormatException => None
    }
  }
//----------------------------------------
    val mySeq=Seq(Some(1), Some(2), Some(3))
 // println(mySeq)
 val fooList=List("1", "2","3")

 // val gg=mySeq.fold(0)((z, f) =>z+f)


 // println(gg)
    def convSec(list1:Seq[Some[Int]]) = {
     /* list1.fold(List[Int]()){ (z, i) =>


        // list1.fo
      }*/
    }
  println(  convSec(mySeq))
 /* val numbers = List(5, 4, 8, 6, 2)
 val rr= numbers.fold(0) { (z, i) =>
    z + i
  }*/
 // println(rr)


}
