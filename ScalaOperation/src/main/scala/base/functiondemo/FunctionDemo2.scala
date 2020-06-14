package base.functiondemo

/**
  * 把函数作为参数的函数示例s
  */
object FunctionDemo2 {
  /**
    * 这里是把函数作为参数的函数
    * m1函数的f参数必须是两个int类型参数，以及返回值也是int类型的函数
    * @param f  函数作为参数
    * @return
    */
  def m1(f:(Int,Int)=>Int):Int={
    f(2,6);
  }

  //定义一个函数f1,参数是两个Int类型,返回值也是Int类型
  val f1 = (x:Int,y:Int) => x + y;

  val f2 = (x:Int,y:Int) => x*y;

  def main(args: Array[String]): Unit = {
      val r1 = m1(f1);

    println(r1);

    val r2 = m1(f2);
    println(r2)
  }
}
