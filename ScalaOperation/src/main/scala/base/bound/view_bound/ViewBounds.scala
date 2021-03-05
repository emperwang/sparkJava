package base.bound.view_bound

/**
  * @author: ekiawna
  * @Date: 2021/3/5 10:44
  * @Description
  */
/**
  *  view bounds 其实就是bounds 上边界的加强版本, 对 bounds的补充: < 变成 <%
  *  可以利用 implicit 隐式转换将实参类型转换为 目标类型
  */

/*
  当给下面这个类传递3,5时 会报错.
  因为3,5 不是 Comparable[T] 的子类
 */
class PairNotPerfect2[T <: Comparable[T]] (val first:T, val sec: T){
  def bigger = if (first.compareTo(sec) > 0) first else sec
}

/*
  将 <: 修改为 <% 就是 视图界定, 这样就可以传递3 和5了, 不会报错.
  我们可以把传入的T 类型的实例 隐式转换为 Comparable[T] 类型
 */
class  PairNotPerfect[T <% Comparable[T]](val first: T, val second:T) {
    def bigger = if (first.compareTo(second)> 0) first else second
}

/*
Ordered 视图界定
  上面 first.compareTo(second)> 0 通过 compareTo来比较, 不能直观的像数学比较那样清晰
  scala提供了 Ordered 视图界定, Ordered 再comparable上提供了一些关系型操作符 < > <= >= 等
 */

class  Pair_Ordered[T <% Ordered[T]](val first:T, val sec:T) {
  // 这里的 > 是因为 Ordered 中提供的方法
  def bigger = if (first > sec) first else sec
}
// 视图界定
object ViewBounds {
  def main(args: Array[String]) :Unit = {
    var pair = new PairNotPerfect("spark", "hadoop")
    println(pair.bigger)
    /*
    当界定类型是 [T <: Comparable[T]] 报错, 因为Int本身并不是Comparable子类
    当类型界定为 视图界定 [T <% Comparable[T]] 就可以正常运行
    因为int 本省不是 Comparable子类型, scala通过 "隐式转换" 将int转换为 RichInt
    而这个类型是 Comparable子类
     */
    var pair2 = new PairNotPerfect(3, 5)
    println(pair2.bigger)
  /*
    注意: 这样定义不是因为 String的上界是 Ordered[String]
    当使用视图界定时, 会发生 "隐式转换" 把String --> RichString
    而RichString 是Ordered[Rich String] 的子类型, RichString中实现了这样的 < > <= >=等方法
    从而真正是让 String类型完成视图界定
   */
    var pair3 = new Pair_Ordered("java", "scala")
    println(pair3.bigger)

    val pairInt = new Pair_Ordered(20,12)
    println(pairInt.bigger)
  }
}
















