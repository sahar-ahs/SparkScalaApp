package scala.example

object MinOfTwoVec {
  def main(args: Array[String]): Unit = {
    val vec1:breeze.linalg.Vector[Double]=breeze.linalg.Vector(1.2,1.1,4.2)
    val vec2:breeze.linalg.Vector[Double]=breeze.linalg.Vector(1.1,1.2,4.2)
   val arrVec2= vec2.toArray
    var i=0
    var firstArr=0
    var secondArr=0
   println("sum vec1: "+breeze.linalg.sum(vec1))
    println("sum vec2: "+breeze.linalg.sum(vec2))
println(vec1.equals(vec2))
    vec1.toArray.foreach{x=>

      if(x<arrVec2(i)) firstArr+=1

      if (x>arrVec2(i)) secondArr+=1
    //  if (x==arrVec2(i))

    }
    if(firstArr>secondArr){
      println(vec1)
    }
    else {
      println(vec2)
    }
 /*  val oo= breeze.linalg.min(vec1,vec2)
    println(oo)*/


  }

}
