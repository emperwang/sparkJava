package base.bound.content_bound

/**
  * @author: ekiawna
  * @Date: 2021/3/5 11:10
  * @Description
  */
// 上下文界定 Context Bounds
/*
上下文界定 [T: Ordering] 这种写法再Spark中广泛使用, 说明存在一个隐式的值 Ordering[T]
implicit  ordered: Ordering[T]
 */
object ContextBound {

  class PairOrdering[T : Ordering] (val first: T, val sec:T) {
    // 这是一个隐式转换的显示定义, 这个函数没有参数, 当函数执行的时候, 这个隐式值就会自动传进来
    def bigger(implicit ordered: Ordering[T]) = {
      if (ordered.compare(first,sec) > 0) first else sec
    }
  }

  def main(args: Array[String]):Unit = {
    val pair = new PairOrdering("Spark", "Hadoop")
    println(pair.bigger)

    val pairInt = new PairOrdering(3,5)
    println(pairInt.bigger)
  }
}
