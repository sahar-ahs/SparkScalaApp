package com.tutorial
//staticچون در اسکالا اعضای
// نداریم برای تعریف کلاس Singleton باید از object استفاده کنیم
// از کلاس هاس سینگل تون نمیتوانیم شی نیو کنیم
object MySingletone{
  def sayHello="Hello Sahar"
}
class classAB{
  def sayHello="Hello Sahar"
  val bb="baba"
}
// constructor
class myClass{
  var mname:String ="nhhh";
  var intField:Int=0
  def this(param:Int){
    this()
    this.intField=param
  }
    def add(value:Int)=intField+=value
  private[tutorial]  def ssss(yy:classAB) ={
   println(yy.sayHello)
  println( yy.bb)
  }
private[tutorial] var ss="hh"
}
class subClass extends myClass{
val intSecond=55

  override def add(value: Int) =println("res is:"+value*2)
}
object ClassTest {
def main(Args:Array[String]){
//println(MySingletone.sayHello)
  var c=new myClass()
  var ab=new classAB()
  c.ssss(ab)
 // c.add(5)
 //println(c.intField)
  //----
  //var ss=new subClass()
 // ss.add(5)
  //println( ss.intField)
  //println(ss.mname)
  //ss.add(5)

}
}
