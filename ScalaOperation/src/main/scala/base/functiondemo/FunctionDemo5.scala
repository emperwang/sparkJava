package base.functiondemo

/**
  * 闭包函数
  */
object FunctionDemo5 {
  var factor = 3;
  val multiplier = (i:Int) => i* factor;
  def main(args: Array[String]): Unit = {
    println("multiplier(1) is = "+multiplier(1))
    println("multiplier(2) is = "+multiplier(2))
  }
}
