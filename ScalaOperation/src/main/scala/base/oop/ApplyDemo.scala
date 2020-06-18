package base.oop

class Boy(name: String) {
  // 定义field时, 需初始化
  private var age: Int = 0

  def this(name: String, age: Int) {
    this(name)
    this.age = age
  }

  def printInfo(): Unit = {
    println("Info: name=" + name + ", age=" + age)
  }
}

// 定义 :
// 此是一个单例对象, 也是Boy class(上面的)的伴生对象
// 而Boy class(上面的)是此object的伴生类
// 没有伴生类的 object 成为 孤立对象
// apply:
// 在伴生对象中定义apply方法后可以在创建对象时使用class(xx)的形式
object Boy {
  def apply(name: String) = new Boy(name)

  def apply(name: String, age: Int) = new Boy(name, age)
}


object starter {
  def main(args: Array[String]): Unit = {
    val boy1 = Boy("zhangsan")
    boy1.printInfo()

    val wangwu = Boy("wangwu", 50)
    wangwu.printInfo()
  }
}