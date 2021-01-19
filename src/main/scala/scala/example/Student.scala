package scala.example

/*
* Create by Sahar on 6/24/2020 5:31 PM.
*/

class Student(var name: String = "", var family: String = "", var grade: Double = 0, var nationalCode: String = "") {

  def AverageGrade(data: Array[Double]): Double = {
    if (!data.isEmpty) {
      var sum: Double = 0
      data.foreach(x => {
        sum += x
      })
      sum / data.length
    }
    else 0

  }
  def MaxGrade(data: Array[Double]): Double = {
    if (!data.isEmpty) {
      var max = data(0)
      data.foreach(x => {
        if (x > max) max = x
      })
      max
    }
    else 0

  }
  def MinGrade(data: Array[Double]): Double= {
    if (!data.isEmpty) {
      var min = data(0)
      data.foreach(x => {
        if (x < min) min = x
      })
      min
    }
    else 0
  }
  def ConvertToStudentObj(line: String): Student = {
    val arr = line.split(" ").map(_.trim)
    new Student(arr(0), arr(1), arr(2).toDouble, arr(3))
  }


  /*  def this(){
      this(name,family,grade)
    }*/
  //override def toString = s"Student(name=$name, family=$family, grade=$grade)"
  override def toString = s"$name $family $grade $nationalCode"

}
