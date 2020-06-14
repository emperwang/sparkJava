package base.controllerStatement

/**
  * 使用for循环遍历一个list链表
  */
object ForDemo4 {
  def main(args: Array[String]): Unit = {
    val numList = List(1,2,3,4,5,6);

    for( a <- numList){
      println("value of a is:"+a);
    }
  }
}
