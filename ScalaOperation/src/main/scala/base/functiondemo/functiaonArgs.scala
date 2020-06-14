package base.functiondemo

/**
  * 可变参数的函数
 */
object functiaonArgs {
  def main(args: Array[String]): Unit = {
    printStrings("runoob","scala","python")
  }

  def printStrings(args:String*)={
    var i :Int = 0;
    for(arg <- args){
      println("Arg value["+i+"]"+arg)
      i = i + 1
    }
  }
}
