package com.tutorial
//تریت در واقع همان اینترفیس هست در جاوا و سی شارپ که بدنه متدها در آن نوشته می شود
trait Person{
  def name:String
}
//برای اکسند کردن از چن کلاس با کلمه کلیدی with
case class Student(name:String,family:String) extends Person
case class Teacher(name:String,family:String) extends Person
//وقتی کلاس را با کیس ایجاد کنیم دیگر نیازی نیست کلاس را نیو کنیم
object CaseClass {
  def main(Args: Array[String]): Unit = {
    val s=Student("sahar","ahsani")
    val t=Teacher("eee","ggg")
getPrintableString(s)
    getPrintableString(t)
    def getPrintableString(p:Person)={ p match {
      case Student(name,family)=> println( s"$name is a student in Year $family.")
      case Teacher(name,family)=>  println(s"$name is a student in Year $family.")
    }

    }
  }
}
