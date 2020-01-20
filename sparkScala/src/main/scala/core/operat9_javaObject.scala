package core

/**
  * descripiton:
  *
  */
object operat9_javaObject {
  def main(args:Array[String]):Unit={
    // 直接调用 java中的对象
    val random = new java.util.Random()
    val i = 0
    for( i <- 0 to 10){
      println("i= " +i)
      println(random.nextInt(10))
      println("********************************")
    }
  }
}
