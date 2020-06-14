package base.functiondemo

/**
  * 嵌套函数
  */
object FunctionsInner {
  def main(args:Array[String]):Unit={
    println(factorial(5))
  }

  def factorial(i:Int):Int={
    def fact(i:Int,accumulator:Int):Int={
      if(i <= 1){
        accumulator
      }else{
        fact(i-1,i*accumulator)
      }
    }
    fact(i,1)
  }
}
