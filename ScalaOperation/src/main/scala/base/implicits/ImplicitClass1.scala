package base.implicits

/**
  * @author: ekiawna
  * @Date: 2021/3/5 17:18
  * @Description
  */
/*
  在scala2.10 后提供了隐式类, 可以使用 implicit 声明类, 隐式类非常强大, 可以用于扩展类的功能,
  比使用隐式转换丰富类库功能更加的方便, 在集合中隐式类发挥了重要的作用

  隐式类的特点:
  1. 其所带的构造参数有且只能由一个
  2. 隐式类必须被定义在 "类" 或者 "伴生对象" 或 "包对象" 里面, 即隐式类不能是 顶级的(top-level objects)
  3. 隐式类 不能是 case class(case class样例类)
  4. 作用域内不能有与之相同名称的标识符

  隐式转换的时机:
  1. 当方法中的参数类型与目标类型不一致时, 或者时赋值时
  2. 当对象调用所在类中不存在的方法或成员时, 编译器会自动将对象进行隐式转换(根据类型)

  隐式解析机制:
  编译器时如何查找到缺失信息的, 解析具有一下两种规则:
  1. 首先会在当前代码作用域下查找隐式实体(隐式方法, 隐式类, 隐式对象). 一般是这种情况
  2. 如果第一条规则查找隐式实体失败, 会继续在隐式参数的类型的作用域里查找. 类型的作用域是指
      与该类型相关联的全部伴生模块,一个隐式实体的类型 T 它的查找范围如下(第二种情况范围广且复杂,在使用时应当尽量避免):
      a) 如果 T 被定义为 T with A with B with C， 那么A,B,C 都是T 的部分, 在T的隐式解析过程中, 它们的伴生对象都会被搜索
      b) 如果T是参数化类型,那么类型参数和 与类型参数相关联的部分都算作 T 的部分,比如List[String] 的隐式搜索会所搜List的
          伴生对象和 String的伴生对象
      c) 如果 T 是一个 单例类型p.T, 即T 是属于某个p对象内, 那么这个p对象也会被搜索
      d) 如果 T 是个类型注入 S#T, 那么S 和 T 都会被搜索

      隐式转换需要遵守两个基本前提:
      1. 不能存在二义性
      2. 隐式操作不能嵌套使用
 */
object ImplicitClass1 {
   def main(arga:Array[String]):Unit = {

     //DB是一个隐式类, 当在隐式类的作用域范围, 创建MySQL实例
     // 该隐式类就会生效, 这个工作 仍然是由编译器完成
     implicit  class DB1(val m:MySQL1) {
        def addSuffix():String = {
          m + "scala"
        }
     }

     val msql = new MySQL1
     msql.sayOk()
     msql.addSuffix()
   }
}

class DB1 {}

class  MySQL1{
  def sayOk(): Unit = {
    println("mySQL say ok...")
  }
}