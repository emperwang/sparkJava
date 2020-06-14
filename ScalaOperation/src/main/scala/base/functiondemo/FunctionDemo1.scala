package base.functiondemo

/**
  * 方法调用实例
  */
object FunctionDemo1 {
  def main(args: Array[String]): Unit = {
    println("10 + 11 = "+addInt(10,12));
  }

  def addInt(a:Int,b:Int):Int = {
    var sum:Int = 0;
    sum = a + b;

    return sum;
  }
}
