package scala.example

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.log4j
import org.apache.log4j.{Level, Logger}

import scala.collection.mutable.ArrayBuffer

object partitioning {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("testPartition").setMaster("local[*]")
    val sc=new SparkContext(conf)
    Logger.getLogger("org").setLevel(Level.OFF)
Logger.getLogger("akka").setLevel(Level.OFF)
    val rddData = sc.parallelize(Seq(1,1234,3,3,3,4,3,1,3,2,12,56,3,4,5,120,6,7,234,456,121,8,9,10,90,129,200))
    println("getNumPartitions: "+rddData.getNumPartitions)
    val arr= new ArrayBuffer[Int]()

    val finalRes=rddData.mapPartitions(itrator=>{
     val res= itrator.map(x=> {
       if (x == 3) {

         ( arr.append(x), "gg")
       }
     })
      res
    })

finalRes.collect().foreach(x=>println(x))
    arr.foreach(x=>println(x))
    finalRes.map(x=>
      if(x!=null) println(x)
    )
  /*  rddData.foreachPartition(partition => {
      //Initialize any database connection
      partition.foreach(fun=>{
        //apply the function
        if(fun==3){
          arr.append(fun)
        }
      })
    })*/
   // println("num of in each partition:")
   /// arr.foreach(x=>println(x))

  }
}
