package base.controllerStatement

/**
  * 这里的for相当于就是一个双层的嵌套for循环
  */
object ForDemo3 {
  def main(args: Array[String]): Unit = {
    for(a <- 1 to 3; b <- 1 to 3){
      println("value of a is :"+a)
      println("value of b is :"+b)
    }
  }
}
