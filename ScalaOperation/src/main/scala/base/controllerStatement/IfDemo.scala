package base.controllerStatement

/**
  * if控制语句的使用
  */
object IfDemo {
  def main(args:Array[String]):Unit={
    var x = 10;
    if(x < 20){
      println("x < 20")
    }else{
      println("x > 20");
    }
  }
}
