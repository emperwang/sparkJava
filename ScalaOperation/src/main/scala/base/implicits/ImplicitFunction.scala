package base.implicits

/**
  * @author: ekiawna
  * @Date: 2021/3/5 13:38
  * @Description
  */
object ImplicitFunction {
  /*
   隐式转换函数 Double - int
   隐式转换函数 在作用域中 才能生效
   */
  /*
  1. 隐式转换函数的函数名可以是任意的, 隐式转换与函数名称无关, 只与 函数签名(函数参数类型和返回值类型) 有关
  2. 隐式函数可以有多个(即:隐式函数列表), 但是需要保证在当前环境下, 只有一个隐式函数能被识别
   */
  def main(args: Array[String]):Unit = {
    implicit def convert(d: Double) :Int = {
      d.toInt
    }
    val num:Int = 3.5
    print("num = "+ num)
  }
  /*
  在隐式准换作用域外使用的话  报错
   val num:Int = 3.5
    print("num = "+ num)
   */
}
