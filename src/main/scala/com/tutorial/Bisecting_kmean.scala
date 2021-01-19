import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.clustering.BisectingKMeans
import org.apache.spark.mllib.linalg.{Vector, Vectors}
object Bisecting_kmean {
  def main(args: Array[String]): Unit = {


    val conf = new SparkConf().setAppName("KMeansExample").setMaster("local[*]")
    //.setMaster("spark://192.168.99.1:7077")
    //.set("deploy-mode","client")
    val sc = new SparkContext(conf)

    // Loads and parses data
    def parse(line: String): Vector = Vectors.dense(line.split(",").map(_.toDouble))

    val data = sc.textFile("src/Resources/IrisNumeric.txt").map(parse).cache()

    // Clustering the data into 6 clusters by BisectingKMeans

    val bkm = new BisectingKMeans().setK(3)
    val model = bkm.run(data)

    // Show the compute cost and the cluster centers
    println(s"Compute Cost: ${model.computeCost(data)}")
    model.clusterCenters.zipWithIndex.foreach { case (center, idx) =>
      println(s"Cluster Center ${idx}: ${center}")
    }
  }
}