package base.functiondemo

object FunctionDemo3 {

  //定义一个方法
  def ttt(f:Int=>Int):Unit={
    val retval = f(10)
    println(retval)
  }

  //定义一个函数
  val f0 = (x:Int) => x*x;

  //定义另一个方法
  def m0(x:Int):Int={
    x * 10
  }

  //将方法转换为函数   利用了下划线
  val f1 = m0 _;


  def main(args: Array[String]): Unit = {
    ttt(f0);
    //使用m0转换后的函数
    ttt(f1);
    // 使用下划线把m0转换为函数
    ttt(m0 _);
    // 这里直接传递的是方法名称  scala相当于把方法转换成了函数
    ttt(m0);
    //通过x=>m0(x) 方式将方法转换为函数
    //这个函数是一个匿名函数
    //等价于: (x:Int) => m0(x)
    ttt(x=>m0(x));
  }
}
