package base.matchOpr

/**
  * scala 中有Left 和Right两个类, 继承于Either,主要用于是保湿两个可能不同的类型(他们之间没有交集)
  * Left 主要表示 Failure,Right表示有,跟Some类型有些类似
  */
object RightLeftDemo {
  def main(args:Array[String]):Unit = {
      //println(divideXByY(1,0))
    //fun3()
    //fun1()
    fun2()
  }

  def divideXByY(x:Int, y:Int): Either[String, Int] = {
     if (y == 0)
       Left("Dude, can't divide by 0.")
     else
       Right(x/y)
  }

  def fun3():Unit={
    divideXByY(1,0) match {
      case Left(s) => println(s"answer: ${s}")
      case Right(i) => println("Answer: " + i)
    }
  }

  def throwableToLeft[T](block: => T): Either[java.lang.Throwable, T] ={
    try{
      Right(block)
    }catch {
      case ex:Throwable => Left(ex)
    }
  }

  def fun1():Unit={
    val s = "Hello"
    throwableToLeft {s.toUpperCase } match {
      case Right(s) => println(s)
      case Left(e) => println(e)
    }
  }

  def fun2():Unit={
    //var s = null
    var s = ""
    throwableToLeft{ s.toUpperCase } match {
      case Right(s) => println(s)
      case Left(e) => e.asInstanceOf[Throwable].printStackTrace()
    }
  }
}
