package base.functiondemo

import java.util.Date

// 偏函数
object PianFunc {
  def log(data: Date, message: String): Unit = {
    print("data = "+data+", message= %s", message)
  }
  def main(args: Array[String]): Unit = {
      // 偏函数 -- 简单理解就是 函数的一个参数给定值,另一个参数传递值
    val data = new Date()
    val logmethod = log(data, _: String)

    logmethod("this is 偏函数")
  }
}
