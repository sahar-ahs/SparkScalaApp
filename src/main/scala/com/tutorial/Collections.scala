package com.tutorial

import scala.collection.mutable.ArrayBuffer

object Collections {
  def main(Args: Array[String]): Unit = {

    val strbuf=ArrayBuffer[String]()

    strbuf+="s1" += "s2"
    strbuf+="s3"
    strbuf++= List("s4,s5")
    for(num<- strbuf) println(num)
    //حذف المنت s1
  //  strbuf-="s1"
   // strbuf--= Array("s4,s5")
  val array=ArrayBuffer[Int](1,2,3,4)
    array.prepend(0)
    array.append(5)
    array.prependAll(Seq(6,7))
    array.clear()
    val a = ArrayBuffer(9, 10)           // ArrayBuffer(9, 10)
    a.insert(0,8)
    val ab = ArrayBuffer.range('a','h')
    ab.remove(0)

    // --list--
   // اون قسمتی که آرایه یا لیست ما قرار میگیره باید علامت : رو قرار بدیم
   val aa :List[Int]= List(1,2,3)
    val b = 0 +: aa
    val bb = List(-1, 0) ++: aa
//---- vector ----
    val v=Vector(1,2,3,4)
    //append
    val s=v:+ 5
    // prepend
   val ss= Array(6,7)++: s
    for(numn<- ss) println(numn)
//--- Map---is meutable and imutable--
val states =collection.mutable.Map(
  "AK" -> "Alaska",
  "IL" -> "Illinois",
  "KY" -> "Kentucky"
)
    states+=("ww"->"www")
    states += ("AR" -> "Arkansas", "AZ" -> "Arizona")
    states ++= Map("CA" -> "California", "CO" -> "Colorado")
    states -= "AR"
    states("AK")="change value"
    for((k,v)<-states){ println(s"key: $k, value: $v")}

    // -- set --is meutable and imutable---
    //تکراری نمی پذیرد
    val set=scala.collection.mutable.Set(1,2)
    set++=Vector(3,4)
    set+=5
    set.add(6)
    set -=(1,4)
    set.remove(2)
    //---------anonymous function----------
    //این مپ فرق دارد با فانکشن مپ بالا.. این برای لیست به کار میرود
    //The _ character in Scala is something of a wildcard character.
    // You’ll see it used in several different places.
    // In this case it’s a shorthand way of saying, “An element from the list, ints.”
    val ints = List(1,2,3)
    // same
    val d=ints.map(_*2)
    val r=ints.map(i=>i*2)
    val doubledInts = for (i <- ints) yield i * 2
    //---Anonymous functions with the filter method--and map--
   // val in = Vector(1,10)
    val in = List.range(1,10)
    //same
    val swa=in.filter(i=>i>5)
    val e=in.filter(_>5)
    val e1=in.filter(_%2==0)
def lessThanFive(i:Int):Boolean=(i<5)
    val ll = List.range(1, 10)
    //same
    val y = ll.filter(lessThanFive)
    val y1 = ll.filter(_ < 5)
    //--------map
    val names = List("joel", "ed", "chris", "maurice")
    //same
    val u=names.map(i=>i.capitalize)
    val uu=names.map(_.capitalize)
    //--
    val t=names.filter(_.length>4)
    names.filter(i=>i.length>4).foreach(println)
    t.foreach(println)
 // val t=names.map(_.toDouble)
//------------------take and takewhile
    val nums=List(1,2,3,4,5,6)
    nums.take(3)
    nums.takeWhile(_ < 5)
// -- oposit of take---
    nums.drop(1)
    nums.dropWhile(_<3)
// --- reduce ---
    //ردیوس یک کالکشن رو میگیره و در انتها یک جواب واحد را بر میگرداند
    //same
    nums.reduce((a,b)=>a+b)
    val sd=nums.reduce(_+_)
    val sdy=nums.reduce(_*_)
// --- tuples---
//تاپل یک کالکشن نیست به همین دلیل دستورات map, filter روی آن کار نمیکند.
    val te=("sahar",12,new test("as"))
    println(s"res is:${te._1}-- ${te._2}-- ${te._3}")
      //----nice-----
    val (name,family,age)=("sahar","ahsani",25)
    //-------grate----
    def getStockInfo = {
      // other code here ...
      ("NFLX", 100.00, 101.00)  // this is a Tuple3
    }
    val (symbol, currentPrice, bidPrice)=getStockInfo
//-----------------------------------
    List("foo", "bar").map(_.toUpperCase)
    List("foo", "bar").map(_.capitalize)
    List("adam", "scott").map(_.length)
    List(1,2,3,4,5).map(_ * 10)
    List(1,2,3,4,5).filter(_ > 2)
    List(5,1,3,11,7).takeWhile(_ < 6)
//---------------------------------------------------
// optenal field
    val street1: Option[String]=None
  //None is just an empty container.
    val str1=None
  }
  class test(name:String)
}

