package base.controllerStatement

/**
  * 使用yield生成新链表
  * 注意这里的括号使用方式
  */
object ForDemo6 {
  def main(args: Array[String]): Unit = {
    val numList = List(1,2,3,4,5,6,7,8,9,10);

    // 使用yield 生成新的链表
    var retVal = for{a <- numList;if a!=3;if a< 8
      }yield a

    for( b <- retVal){
      println("value of b is:"+b);
    }
  }
}
