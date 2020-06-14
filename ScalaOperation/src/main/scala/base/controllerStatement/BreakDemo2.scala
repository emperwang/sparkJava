package base.controllerStatement

import scala.util.control.Breaks

/**
  * 嵌套break
  */
object BreakDemo2 {
  def main(args: Array[String]): Unit = {
    val numList1 = List(1,2,3,4,5);
    val numList2 = List(11,12,13);

    val outer = new Breaks;
    val inner = new Breaks;

    outer.breakable{
      for( a <- numList1){
        println("value of a is:"+a);

        inner.breakable{
          for( b <- numList2){
            println("value of b is "+b);
            if (b ==12){
              inner.break();  //这里只会跳出本层的for循环,外层的for循环不会跳出
            }
          }
        }
        println("after the inner break")
      }
    }

    println("after the outer breaks");
  }
}
