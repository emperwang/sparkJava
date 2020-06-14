package base.controllerStatement

/**
  * scala for语句使用
  */
object ForDemo {
  def main(args: Array[String]): Unit = {
    var a = 0;
    for(a <- 1 to 10){
      println("value of a is:"+a);
    }
  }
}
