package base.bound.classtag

import scala.reflect.ClassTag

/**
  * @author: ekiawna
  * @Date: 2021/3/5 11:16
  * @Description
  */
/*
  上下文界定[T:ClassTag] : 相当于动态类型,记录了当前T的类型, 你使用时 传入什么类型就时什么类型,在实际运行的时候我们获取
    T 具体的类型
   主要时应用于创建泛型数组, 因为数组必须有具体的类型, 否则无法创建相应的数组,利用 [T: ClassTag]
 */
object ClassTagBound {
  /*
  [T:ClassTag] 这种写法说明: 当这个函数在运行时, 对存在一个 ClassTag[T] 一个隐式值, 这种方式时最常用的
  主要是在运行时指定, 在编译时无法确定的 type的信息
  编写 编译的时候没有具体类型,运行的时候必须要有具体的类型, 所以需要一种机制,运行的时候
  会根据类型进行类型推断
   */
  def makeArray[T: ClassTag](elems: T*) = Array[T](elems: _*)

  def main(args: Array[String]):Unit= {
    makeArray(42, 13).foreach(println)
    makeArray("Japan", "Brazil", "Germany").foreach(println)
  }
}

































