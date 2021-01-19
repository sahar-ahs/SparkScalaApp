package com.tutorial

import java.util

import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.mllib.linalg.DenseVector

import scala.collection.mutable.ArrayBuffer

class exampleTest {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.ml.feature.VectorAssembler
    val assembler = new VectorAssembler().setInputCols(Array[String]("AVG_REVENUE")).setOutputCol("features")
  // val dataset = assembler.transform(dataset)
    val kmeans = new KMeans().setK(3).setSeed(1L).setFeaturesCol("features")
    //val model = kmeans.fit(dataset)

    val testDouble = Seq(1,2,3,4,5).map(x=>x.toDouble).to[Vector].toList
 // val vec=  Vectors.dense(testDouble)
val arr=new ArrayBuffer[List[Double]]()
 /* arr.append(vec.toArray)
 val uuu=  Storage.centroid
    val uu=arr.toList
    uuu.add(testDouble)
    java.util.Collections.sort(uuu)*/
  }


}
