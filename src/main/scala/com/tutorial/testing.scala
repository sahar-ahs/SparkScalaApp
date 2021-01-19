package com.tutorial
import java.io.{FileNotFoundException, FileReader, IOException}

import org.apache.spark.sql.SparkSession
import org.apache.spark.mllib.clustering
import org.apache.spark.streaming
import breeze.linalg._
import breeze.stats.distributions.Gaussian
import breeze.stats.distributions._

object testing {
  def main(args:Array[String]): Unit ={
   /* println("Hello Sahar")
    val list:List[Int]=List(1,2,3,456)
    val num=Nil
    println(list.length)
    println(num.isEmpty)*/

//concatination
    /*val list1:List[Int]=List(1,2,3,4,5)
    val list2:List[Int]=List(6,7,8,9,10)
    println(list1:::list2)
    println(list1.:::(list2))
    println(List.concat(list1,list2))
    println(list1.reverse)
    val num1=List.tabulate(4,5)(_*_)
    val num2=List.fill(5)("sahar")
    println(num1)
    println(num2)*/

      // set
   /* val set1:Set[Int]=Set(1,2,3,4,5)
    val set2:Set[Int]=Set(6,7,8,9,10)
    val set3:Set[Int]=Set()
    println(set1.head)
    println(set1++set2)
    println(set1.|(set2))
    println(set1&set2)
    println(set2.max)*/

    //map
    val persion1:Map[String,Int]=Map("sahar" ->20,"amir" -> 16)
    val persion2:Map[String,Int]=Map("samira" ->22,"saeed" -> 14)

    println(persion1.keys)
    println(persion2.values)
    println(persion1.contains("samira"))
    persion1.keys.foreach{x =>
      println("key is:" + x)
      println("value is:" + persion1(x))
    }
   /* val iterator=Iterator(1,2,3,4,5)
    while (iterator.hasNext)
      {
        println(iterator.next())
      }
    val poi = Poisson(3.0)
    println(poi.draw)
    val x = poi.sample(10)
    println(x)
    println(x.sum)*/
//println("sum is:"+add(12,13,14))

    // baraye vorodi na moayan
    def add(a:Int*):Int={
      var sum:Int=0
      for (i<- a)
        {
          sum+=i
        }
      sum
    }

    //-----------
 /*   for (i <- 1 to 5){
      println(i+"="+ factoriel(i))
    }

    def factoriel(n:BigInt):BigInt= {
      if (n == 1)
        1
      else
        n * factoriel(n - 1)
    }*/
//----- to dar to func
  //  println("answer is: "+sqrtFunc(2,2))
    def sqrtFunc(x:Int,y:Int)={


    def addFunc(a:Int,b:Int)={
      a+b
    }
    Math.sqrt(addFunc(x,y)*addFunc(x,y))

  }
// tavabe chand meghdari(chandin khoroje medahad) dar zaban haye deghar bayad az araye estefade card va hamchen ghableyate vojod nadard.
/*  var (mysum,mymull) =addMull(2,3)
    println("sum is:"+mysum)
    println("mull is:"+mymull)*/

    //----
    var human=new Human("sahar","ahsani")
    println(human)
   println(human.sayHello("aaaaaaaaaa"))
    println(human.res())
    //-----------------
    var cat=new Man()
   println(cat.mmm())
    var dog=new Me()
   println( dog.mmm())
}
  def addMull(a:Int,b:Int):(Int,Int)={
    (a+b, a*b)
  }
//------- try catch --------
  try{
    val f = new FileReader("dfd.txt")
  }

  catch {
case ex:IOException => print("ddd")
case ex:FileNotFoundException =>{
print("not found file")
}
case ex:Exception =>{
  print("kk")
}

  }
  finally {
    print("Exiting finally")
  }
}

// class
class Human(val fname:String,val lname:String){
  def sayHello(): String ={
    "Hello"

  }
  def sayHello(name:String): String ={
return s"$name"
  }
  def res(): String ={
    return "name is:%s lname is:%s".format(fname,lname)
  }

  override def toString(): String = s"$fname,$lname"
}
trait ensan{
  def mmm():String

}
class  Man extends ensan {
  override def mmm():String = {
    return "cat"
  }
}
  class  Me extends ensan {
    override def mmm():String ={
     return "dog"
    }
}