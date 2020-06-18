package base.functiondemo

object CurryFunc {
  // 柯里化函数
  // 柯里化(Currying) 指的是将原来接收两个参数的函数变成新的接受一个参数的过程, 新的函数返回一个以原有第二个参数为
  // 参数的函数

  // 正常的函数, 返回两个参数的和
  def add(x: Int, y: Int): Int ={
    x + y
  }

  // 柯里化
  // 此函数实际调用先变成这样:
  // def add(x:Int) = (y:Int) => x+y
  def add2(x:Int)(y:Int):Int  = x + y

  def main(args: Array[String]): Unit={
    // 正常调用
    println(add(1, 2))

    // 柯里化函数的调用
    println(add2(4)(5))

  }
}
