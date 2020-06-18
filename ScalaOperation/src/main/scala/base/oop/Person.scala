package base.oop

/**
  * 1. 类名不是必须和文件名一致
  * 2. 可以有任意多的辅助构造函数
  */
class Person2 {

}

// 3. 主构造函数和类是在一起
// 4. 主构造函数的参数 会成为field, 在类中都可以访问
// 5. 编译器会会构造参数添加 setter/getter方法
// 6. 语句和表达式在类的主题中执行
class Person3(val age: Int, val name: String) {

  def printinfo(): Unit = {
    println("Info: age=" + age + ",name=" + name)
  }

  val other = "hello scala"
  println("oother= " + other)
  def printOther(): Unit = {
    println("other= " + other)
  }

  printOther()
  println("still in primary constructor.")
}

// test 辅助构造器
class Person4(var age:Int, var name:String){
  def this() {
    this(10, "default")
  }
  def printinfo(): Unit = {
    println("Info: age=" + age + ",name=" + name)
  }

  val other = "hello scala"
  println("oother= " + other)
  def printOther(): Unit = {
    println("other= " + other)
  }
  printinfo()
  printOther()
  println("still in primary constructor.")
}


object SMain {
  def main(args: Array[String]): Unit = {
    //val person = new Person2

    /*val zperson = new Person3(10, "zhangsan")
    println("*" * 50)
    println("age=" + zperson.age + ", name=" + zperson.name)
    //zperson.name = "wangwu"  // name为 val类型，不可修改
    println("age=" + zperson.age + ", name=" + zperson.name)*/

    // 由此可见调用 辅助构造函数, 语句和表达式也会在主体中运行
    val person = new Person4()
  }
}
