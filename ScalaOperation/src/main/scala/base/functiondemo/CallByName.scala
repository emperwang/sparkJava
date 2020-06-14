package base.functiondemo

/**
  * 传名函数
  * 将未计算的参数表达式直接应用到函数内部
  */
object CallByName {
  def main(args: Array[String]): Unit = {
    delayed(time());
  }

  def time():Long = {
    println("获取时间,单位是纳秒")
    System.nanoTime()
  }

  def delayed(t: => Long) ={
    println("在delayed方法内")
    println("参数:"+t)
    t
  }
}
