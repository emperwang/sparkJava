package base.functiondemo

import java.util.Date

/**
  * 偏应用函数
  * 给原函数绑定一个参数 相当于生成一个新函数一样
  * 调用时 只需要传递没有赋值的那个参数
  */
object FunctionsDemo4 {
  def main(args: Array[String]): Unit = {
    val date = new Date();
    log(date,"message1");
    log(date,"message2");
    log(date,"message3");
    println("---------------------------")
    //偏应用函数  给函数绑定了一个参数
    val logWithDataBound = log(date,_:String)
    logWithDataBound("message1")
    logWithDataBound("message2")
    logWithDataBound("message3")
  }

  def log(date:Date,message:String)={
    println(date+"------"+message);
  }
}
