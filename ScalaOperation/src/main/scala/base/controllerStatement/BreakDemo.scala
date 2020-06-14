package base.controllerStatement

import scala.util.control.Breaks

/**
  * break的使用示例
  */
object BreakDemo {
  def main(args: Array[String]): Unit = {
    val numList = List(1,2,3,4,5,6,7,8,9,10);
    val loop = new Breaks;
    loop.breakable{
      for (a <- numList){
        println("value of a is :"+a);
        if(a == 4){
          loop.break();
        }
      }
    }
    println("After the break")
  }
}
