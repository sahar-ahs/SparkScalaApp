package scala.example

import java.io.{BufferedReader, BufferedWriter, File, FileReader, FileWriter, InputStreamReader, PrintWriter}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source


/*
* Create by Sahar on 6/23/2020 10:13 AM.
*/

object Example2 extends App {
  val pathFile = "src/Resources/StudentsInfo.txt"
  val pathReportFile = "src/Resources/StudentsReport.txt"

  val br = new BufferedReader(new InputStreamReader(System.in))
  println("Please Select a Number \n" +
    "1- Enter New Student \n" +
    "2- Search Student \n" +
    "3- Delete Student \n" +
    "4- Update Student \n" +
    ">>>"

  )

  val input = br.readLine()
  input match {
    case "1" => SubmitStudent()
    case "2" => SearchStudent()
    case "3" => DeleteStudent()
    case "4" => UpdateStudent()
    case _ => "invalid input"
  }

  def SubmitStudent() {
    val writer = new FileWriter(new File(pathFile))
    val writerReport = new FileWriter(new File(pathReportFile))

    var bool = true
    println("Please Enter a Student Info:")
    val arr = ArrayBuffer[Student]();
    try {
      while (bool) {
        val line = br.readLine()
        if (line == "end") bool = false
        else {
          // println("you Entered:" + line)
          println("Add Next Student")
          if (line != null || line != "") {
            val student = new Student().ConvertToStudentObj(line)
            arr.append(student)
            //  arr :+ student
          }

        }

      }
    }
    catch {
      case ex: Exception => {
        print(" خطایی رخ داده!")
        println(ex)
      }
    }

    val gradeArr = ArrayBuffer[Double]()
    //val greArr= Array[Double]()

    arr.foreach(x => {
      gradeArr.append(x.grade)
      //  greArr :+ 2
    })
    val avg = new Student().AverageGrade(gradeArr.toArray)
    val max = new Student().MaxGrade(gradeArr.toArray)
    val min = new Student().MinGrade(gradeArr.toArray)
    // println(s"avg is:$avg \n min is: $min \n max is: $max")
    try {
      arr.foreach(x => {
        writer.write(x + "\n")
      })
      writerReport.write("************ the result is ************* \n")
      writerReport.write(s" avg is: ${avg.toString} \n")
      writerReport.write(s" min is: ${min.toString} \n")
      writerReport.write(s" max is: ${max.toString} \n")
      println("Done!...")
      writer.close()
      writerReport.close()
    }
    catch {
      case ex: Exception => println(ex)
    }
  }

  def SearchStudent() {
    println("Please Enter NationalCode Of Student:")
    var bb = true
    //   while (bb) {
    val in = br.readLine()
    try {
      val file = Source.fromFile(pathFile)

      if (!file.isEmpty) {
        var isExist = false
        for (line <- file.getLines()) {
          if (!line.isEmpty && (!line.startsWith("*") || !line.startsWith(" "))) {
            val student = new Student().ConvertToStudentObj(line)
            //   val student = line.asInstanceOf[Student]
            if (student.nationalCode.equals(in)) {
              println("student exist!")
              println(student.toString)
              isExist = true
            }

          }
        }
        if (!isExist) println("Student not exist!")
      }
      else println("file is empty")

    } catch {
      case ex: Exception => println(ex)
    }

  }

  def DeleteStudent() {
    println("Please Enter NationalCode Of Student:")
    val inputNationalCode = br.readLine()

    val arr = ArrayBuffer[Student]()
    try {
      val file = Source.fromFile(pathFile)
      if(!file.isEmpty) {
        for (line <- file.getLines()) {
          val st = new Student().ConvertToStudentObj(line)
          arr.append(st)
        }
        val writer = new BufferedWriter(new FileWriter(pathFile))
        val newArr = arr.filter(x => x.nationalCode != inputNationalCode)
        newArr.foreach(x => writer.write(x.toString+ System.lineSeparator()))
        writer.close()
        println("delete is done!")
      }
     else println("file is empty!")

      // removeLine(in)
    } catch {
      case ex: Exception => println(ex)
    }
  }

  def UpdateStudent() {
    println("Please Enter NationalCode Of Student:")
    val inputCode = br.readLine()
    println("Please Enter New Student Complete Info:")
    val inputStudentInfo = br.readLine()
    val arr = ArrayBuffer[Student]()
    try {
      val file = Source.fromFile(pathFile)
      if (!file.isEmpty) {
        for (line <- file.getLines()) {
          val st = new Student().ConvertToStudentObj(line)
          arr.append(st)
        }
        val writer = new BufferedWriter(new FileWriter(pathFile))
      //  val newArr = arr.filter(x => x.nationalCode != inputNationalCode)
        val newInfo=new Student().ConvertToStudentObj(inputStudentInfo)
        arr.foreach(x=>{
          if(x.nationalCode==inputCode){
            x.name=newInfo.name
            x.family=newInfo.family
            x.grade=newInfo.grade
            x.nationalCode=newInfo.nationalCode
          }
          writer.write(x.toString + System.lineSeparator())
        })
        writer.close()
        println("update is done!")
      }
      else println("file is empty!")
    }
catch{
        case ex:Exception=>throw (ex)
    }

    /*def removeLine(removeTxt: String): Any = {
       try {
         val f = new File(pathFile)
         val br = new BufferedReader(new FileReader(f))
         val temp = new File("src/Resources/temp.txt")
         val bw = new BufferedWriter(new FileWriter(temp))
         var curLine = ""
         var isExist = false
         while ((curLine = br.readLine()) != null && curLine != null) {

           val trimmedLine = curLine.trim

           if (!curLine.startsWith("*") || !curLine.startsWith(" ")) {
             val student = new Student().ConvertToStudentObj(trimmedLine)
             //   val student = line.asInstanceOf[Student]
             if (student.nationalCode.equals(removeTxt)) {
               isExist = true
             }
             else
               bw.write(curLine + System.getProperty("line.separator"))
           }
         }
         bw.close()
         br.close()
         val deleted = f.delete
         val rename = temp.renameTo(f)
         if (!deleted) println("could not delete file!")
         if (!rename) println("could not rename file!")
         if (isExist) println("successfully deleted!")
         else println("not exist this student!")

       }
       catch {
         case ex: Exception => throw (ex)

       }
     }*/




  }


}
