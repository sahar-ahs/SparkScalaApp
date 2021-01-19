package com.tutorial
import Array._
object test2 {
  def main(Args: Array[String]): Unit = {

    // same as tuple
    val (key: Int, value: String) = Pair(2, "sahar")
    println((key, value))
    // same as switch case
    val number: Int = 3
    var rate = number match {
      case 1 => "is 1 number"
      case 2 => "is 2 number"
      case 3 => "is 3 number"
      case _ => "is 2 number"
    }
    println(rate)
    // for
    // for(i <- 1 to 5){
    //  println(i)
    // }
    // array
    var arr1: Array[String] = new Array[String](3)
    arr1(0) = "sahar"
    arr1(1) = "12"

    // araye ke maghader mokhtalef begirad List ast
    val arrTest = List("sahar", 12, "dara")
    /* val arrTest= Array("sahar",12,"dara")
    for(i <-0 to (arrTest.length-1)){
      println(arrTest(i))
    }*/
    // shabeh foreach
    for (i <- arrTest) {
      print(i + "\n")

    }

    // estefade az araye 2D import Array._
    /*var matrix=ofDim[Int](3,3)
    for(i <- 1 to 3) {
      for (j <- 1 to 3) {
        matrix(i)(j) = j
      }
    }*/
    // araye adade ba range
    var arrayRange = range(1, 10, 2)
    for (i <- arrayRange) {
      // println(i)
    }
    val l1: List[Int] = List(1, 2, 3)
    val l2: List[String] = List("tt", "ww", "tu")

    val persion1: Map[String, Int] = Map("sahar" -> 20, "amir" -> 16)

    // println(persion1.keys)
    for ((key, value) <- persion1) {
      println(s"res is: ${key},${value}")
    }
    //----------------
    for (i <- 1 to 10 if (i % 2 == 0)) {
      print(i)
    }
    //--------dota dar yeke----
    for (i <- 1 to 3; j <- 1 to 2) {
      print(s"$i --> $j")
    }
    //--------------
    /* persion1.keys.foreach{x =>
      println("key is:" + x)
      println("value is:" + persion1(x))
    }*/


    //tuple har noe onsori ra mipazirad
    val tuplew = Tuple3(1, 12.3, "sahar")
    tuplew.productIterator.foreach(x =>
      println("value is:" + x)
    )

    //---------Inline-function------------
    def sum(x: Int, y: Int) = if (x > y) s"sahar $x" else s"sara $y"

    sum(5, 4)

    //-------فاصله اقلیدوسی-------
    def distance(x1: Int, y1: Int, x2: Int, y2: Int) = {
      def defPow(a: Int, b: Int) = (a - b) * (a - b)

      Math.sqrt(defPow(x1, x2) + defPow(y1, y2))
    }

    println("Oqliduc Destance is:" + distance(3, 3, 9, 11))

    //---توابع به عنوان پارامتر---
    def test() = println("hello world")

    def printTest(f: () => Unit) = {
      f()
      f()
    }

    printTest(test)

    //-----------------------------------
    def mul2(a: Int) = a * 2

    def sum2(a: Int) = a + 2

    def printDef(f: (Int) => Int) = {
      println(s"[${f(3)}]")
    }

    printDef(mul2)
    printDef(sum2)

    //------------------------------------
    def AddSum(a: Int, b: Int): String = {
      s"[$a--$b]"
    }

    def printRes(f: (Int, Int) => String): Unit = {
      println(f(2, 3))
    }

    printRes(AddSum)

    // Anonymouse Function-- tabei ke faghat vorodi va khoroje dara
    def PrintAnony(f: (Int) => Int): Unit = {
      print(s"Anony res is:{${f(12)}}\n")
    }

    PrintAnony(a => a * 2)

    //------------------------
    def PrintAnonyfunc(f: (Int, Int) => String): Unit = {
      println(s"Anony res is:{${f(12, 12)}}")
    }

    PrintAnonyfunc((a: Int, b: Int) => "[[" + (a + b) + "]]")

    //------توابع چند مقداری-----
    def multiOut(a: Int, b: String): (Int, String) = {
      (a * a, b.toUpperCase())
    }

    val (myMull, myStr) = multiOut(3, "sahar ahsani")
    println(s"myMull is:${myMull}")
    println(s"myStr is:${myStr}")

  }
}