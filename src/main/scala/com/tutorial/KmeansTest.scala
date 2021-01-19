package com.tutorial





import breeze.numerics.{pow, sqrt}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkConf, SparkContext}

object KmeansTest {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("KMeansExample").setMaster("local[*]")
      //.setMaster("spark://192.168.99.1:7077")
      //.set("deploy-mode","client")
    val sc = new SparkContext(conf)
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    // $example on$
    // Load and parse the data
    val data = sc.textFile("src/Resources/Iris.csv")
   // val io = data.map(LabeledPoint.parse)
    /*val ty=data.map({p=>
    val k= p.split(",").map(a=>a.dropRight(1))
      Vectors.dense(k.map(_.toDouble))
    })*/
    val qq = data.map({ point =>
      val u = point.split(",")
      val numericData = u.patch(4, Nil, 1) //.toBuffer
      // u.remove(4)

      val feature = Vectors.dense(numericData.map(_.toDouble))
      feature
    })

    // Cluster the data into two classes using KMeans
    val numClusters = 3
    val numIterations = 10
    //val clusters =new KMeans().setFeaturesCol("features")

    val clusters = KMeans.train(qq, numClusters, numIterations)
  //  sc.parallelize(Seq("2.1 1.1 3.1"))
     //---------------------------------------------------------------------------
   val preIndices= clusters.predict(qq)
  println("size of each macro cluster:"+ preIndices.countByValue().toVector)
   println("Spark Wssse:"+ clusters.computeCost(qq))

    def classicDistance(point1: Array[Double], point2: Array[Double]) = {
      var i=0
      var sum=0.0
      for(s <- point1){
      val mt= pow( point2(i) - s,2)
       sum+=mt
        i=i+1
      }
      sqrt(sum)
    }

    def distance(xs: Array[Double], ys: Array[Double]) = {
      sqrt((xs zip ys).map { case (x,y) => pow(y - x, 2) }.sum)
    }

    //-----------------------------------------------------------------------------
    var ss = 0.0

      val yy = qq.map { point =>
        val  center = clusters.clusterCenters(clusters.predict(point))
      //  val sq=squaredDistance(point.toArray.toVector,center.toArray.toVector)
     // val sq=  math.pow(classicDistance(point.toArray,center.toArray),2)
      val sq=math.pow(distance(point.toArray,center.toArray),2)
        //val sq=distance(point.toArray,center.toArray)

      //  ss=ss+ sq
       sq

      }.reduce((x,y)=>x+y)
     print("Manual Sum of Squared Error = " +  yy)
  //  print("Within Set Sum of Squared Error = " +  "%.2f".format(yy).toDouble)


//---------------------------------------------------------------------------
    val vectorsAndClusterIdx = qq.map { point =>
      val prediction = clusters.predict(point)
      val g = point.toDense.values.toVector

      //zipValues((point,center).foreach

      // val u= point-center
      (point, prediction)
    }
   // vectorsAndClusterIdx.foreach(x => println(x))
    //--------------------------------------------------------------
    // println("res pridict:"+ss.foreach(x=>println(x)))
    // Evaluate clustering by computing Within Set Sum of Squared Errors
    // val WSSSE = clusters.computeCost(parsedData)
    // println(s"Within Set Sum of Squared Errors = $WSSSE")

    // Save and load model
    //clusters.save(sc, "target/org/apache/spark/KMeansExample/KMeansModel")
    // val sameModel = KMeansModel.load(sc, "target/org/apache/spark/KMeansExample/KMeansModel")
    //  println(clusters.clusterCenters.foreach(x=>println(x)))

    // $example off$

    sc.stop()
  }
}
