package base.functiondemo

/**
  * 默认参数值 函数
  */
object Defaultvalue {
  def main(args: Array[String]): Unit = {
    println("返回值:"+addInt())
  }

  def addInt(a:Int=5,b:Int=7):Int={
    var sum:Int = 0;
    sum = a + b;
    return sum;
  }
}
