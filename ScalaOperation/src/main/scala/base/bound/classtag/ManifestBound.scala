package base.bound.classtag

/**
  * @author: ekiawna
  * @Date: 2021/3/5 11:16
  * @Description
  */
object ManifestBound {
  /*
        Q:可以创建泛型数组吗?
        理论上是不可以的, 因为没有指定具体类型, 在scala程序运行中, 数组必须有具体的类型,否则无法创建相应的数组

        引出Manifest的概念: 可以创建泛型数组
        [T: Manifest] 这样的写法被称之为Manifest上下文界定, 实质上这是需要一个Manifest[T] 类型的 隐式对象,
        这又是一个 隐式转换 的过程. 有这样的一个隐式转换来辅助我们构建Manifest[T] 来确定T的类型
        通过这个隐式的值来辅助构建泛型数组,  来确定T的具体类型
        所以在创建泛型函数时, 需要Manifest的类型来辅助构建泛型数组, 借助Manifest类型对象来指定泛型数组的具体类型

        通过 Manifest[T] 可以记录 T 的类型, 在实际运行的时候我们可以获取 T 具体的类型
     */
  def arrayMake[T: Manifest](first: T, sec:T) ={
    val r = new Array[T](2)
    r(0) = first
    r(1) = sec
    r
  }

  /*
    Manifest 的原生写法
   */
  /*def mainf[T](x: List[T])(implicit m: Manifest[T]) = {
    if (m <:< manifest[String]){
      println("List Strings")
    }else{
      println("some other type")
    }
  }*/
  /**
    * A =:= B means A must be exactly B
    * A <:< B means A must be a subtype of B (analogous to the simple type constraint <:)
    * A <%< B means A must be viewable as B, possibly via implicit conversion
    *     (analogous to the simple type constraint <%)
    */
  import scala.reflect.runtime.universe._
  def mainf[T] (x:List[T])(implicit m: TypeTag[T]) = {
    println(m.tpe)
    if (m.tpe =:= typeOf[String]){
      println("List String")
    }else{
      println("other type")
    }
  }

  def main(args:Array[String]):Unit = {
    arrayMake(1, 2).foreach(println)

    mainf(List("spark","Hadoop"))
    mainf(List(1,2,3))
    mainf(List("scala", 3))
  }
}
