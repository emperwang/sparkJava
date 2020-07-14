package base.functiondemo

object Method2Func {
  def add(a: Int, b: Int): Int = {
    a + b
  }

  def main(args: Array[String]):Unit={
    // 方法转换为函数; 即在方法后添加 空格 下划线
    val func = add _
    println(func(1, 2))

    // 直接定义函数
     val f2 = (a:Int, b:Int) => a + b
    println(f2(3,4))
  }
}
