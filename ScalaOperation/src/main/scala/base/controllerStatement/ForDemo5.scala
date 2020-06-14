package base.controllerStatement

/**
  * 使用for循环实现过滤
  */
object ForDemo5 {
  def main(args: Array[String]): Unit = {
    val numList = List(1,2,3,4,5,6,7,8,9,10);

    for (a <- numList if a!=3; if a < 8){
      println("value of a  is:"+a);
    }
  }
}
