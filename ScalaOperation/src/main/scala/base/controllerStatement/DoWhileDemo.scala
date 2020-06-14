package base.controllerStatement

/**
  * do-while语句使用
  */
object DoWhileDemo {
  def main(args: Array[String]): Unit = {
    var a = 10;

    do{
      println("value of a:"+a);
      a += 1;
    }while(a < 20)
  }
}
