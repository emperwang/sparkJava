package base.functiondemo

object ClosePackageFunc {

  def main(args: Array[String]): Unit={

    val fact = 3
    // 此 function 引用外部变量来作为自己的函数体内的局部变量
    // 而 i 作为参数传递进来
    // 此称之为闭包函数
    val mutiplyer = (i: Int) => i * fact

    print("result1 =" +mutiplyer(10) )
    print(("result2 = " + mutiplyer(20)))
  }

}
