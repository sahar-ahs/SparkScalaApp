package example.myclass

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object example2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ex2").setMaster("local[*]")
    //.setMaster("spark://192.168.99.1:7077")
    //.set("deploy-mode","client")
    val sc = new SparkContext(conf)
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val ds1 = sc.parallelize(Seq("spark", "R", "java", "python", "spark", "program", "R", "R"))
  val ds2=sc.parallelize(Seq("1","2","3","4","5"))
   val dd= ds1.union(ds2)
    println("Union is:")
    dd.foreach(x=>println(x))
//------------------------------------------------------------------
    val ds3=sc.parallelize(Seq("ads","asda","spark","R"))
    val ddd=ds1.intersection(ds3)
    println("intersection is:")
    ddd.foreach(x=>println(x))
//-------------------------------------
    println("distinct dataset: ")
   val ss= ds1.distinct()
    ss.foreach(x=>println(x))
//-----------------------------------------
    println("join dataset: ")
    val data = sc.parallelize(Array(('A',1),('b',2),('c',3)))
    val data2 =sc.parallelize(Array(('A',4),('A',6),('b',7),('c',3),('c',8)))
    val result = data.join(data2)
    println(result.collect().mkString(","))
//----------------------------------------------------------
    println("coalesce dataset: ")
    val rdd1 =sc.parallelize(Array("jan","feb","mar","april","may","jun"),3)
    val result1 = rdd1.coalesce(2)
    result1.foreach(println)
//--------------------------------------------------------
    println("top dataset: ")
    val rdd2 =sc.parallelize(Array("jan","feb","mar","april","may","jun"))
    val pp=rdd2.map(x=>(x,x.length)).top(4)
    pp.foreach(println)
//-------------------------------------------------------------------
    println("fold is: ")
    val rdd3 = sc.parallelize(List(("maths", 80),("science", 90)))
    val additionalMarks = ("extra", 4)
    val sum = rdd3.fold(additionalMarks){ (acc, marks) => val add = acc._2 + marks._2
      ("total", add)
    }
    println(sum)
//---------------------------------------------------------------
    println("aggregate is: ")
    val listRdd = sc.parallelize(List(1,2,3,4,5,3,2))
    def param0= (accu:Int, v:Int) => accu + v
    def param1= (accu1:Int,accu2:Int) => accu1 + accu2
    val result3 = listRdd.aggregate(0)(param0,param1)
    println("output 1 =>" + result3)
//--------------------------------------------------------------------
    println("aggregate is: ")
val inputRDD = sc.parallelize(List(("Z", 1),("A", 20),("B", 30),("C", 40),("B", 30),("B", 60)))
    //aggregate
    def param3= (accu:Int, v:(String,Int)) => accu + v._2
    def param4= (accu1:Int,accu2:Int) => accu1 + accu2
    val result2 = inputRDD.aggregate(0)(param3,param4)
    println("output 2 =>" + result2)
  }
}