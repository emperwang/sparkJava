package base

/**
  * @author: ekiawna
  * @Date: 2021/3/5 18:03
  * @Description
  */
/*
  在java中 包可以包含 类,对象,和特质trait, 但不能包含函数/方法或变量的定义
  scala提供了 包对象的概念 来解决这个问题
  包对象说明:
  1. 在保重直接写方法, 或者定义变量, 就使用包对象的技术来解决
  2. package object packgeobject 表示创建一个包对象 packgeobject, 它是 base.packgeobject 这个包
      对应的包对象
  3. 每一个包都可以有一个包对象
  4. 包对象的名字需要和子包一样
  5. 在包对象中可以定义变量  方法
  6. 在包对象中定义的变量和方法, 可以在对应的包中使用
  7. 在底层中这个包对象会生成两个类 package.class 和 package$.class
 */
package object packgeobject {
  var name = "king"
  def packageSayHi():Unit = {
    println("package object scala say hi ~")
  }
}
