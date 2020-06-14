package base.controllerStatement

/**
  * while控制语句的使用
  */
object WhileDemo {
  def main(args: Array[String]): Unit = {
    var a = 10;
    while (a < 20){
      println("value of a is :"+a);
      a += 1;
    }
  }
}
