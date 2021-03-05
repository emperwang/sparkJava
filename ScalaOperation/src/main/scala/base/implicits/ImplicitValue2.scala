package base.implicits

/**
  * @author: ekiawna
  * @Date: 2021/3/5 13:53
  * @Description
  */
/*
  1. 当程序中同时有  隐式值  默认值  传值
    编译器的优先级为:  传值 > 隐式值 > 默认值
   2. 在隐式值匹配时, 不能有二义性
   3. 如果三个(隐式值, 默认值, 传值) 都没有, 报错
 */
object ImplicitValue2 {
    def main(args: Array[String]):Unit= {
        //隐式变量
      implicit  val name:String  = "scala"

      // 当同时有 隐式值 和 默认值,  隐式值优先级高
      def hello(implicit content:String="jack"):Unit = {
          println("Hello " + content)
      }
      // 没有隐式值时  使用默认值
      def hello2(implicit  content:Int = 10):Unit = {
        println("Hello2  " + content)
      }
      hello
      hello2

    }
}
