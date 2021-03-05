package base.implicits

/**
  * @author: ekiawna
  * @Date: 2021/3/5 13:49
  * @Description
  */
/*
 隐式值也叫 隐式变量，  将某个形参变量标记为 implicit, 所以编译器会在方法省略隐式参数的情况下去
 搜索作用域内的隐式值 作为缺省参数
 */
object ImplicitValue1 {
  def main(args: Array[String]) :Unit= {
      implicit val str2:String = "mike ~ " // 这就是隐式值
      def hello(implicit name:String):Unit = {
          println("name = " + name)
      }
      hello
  }
}
