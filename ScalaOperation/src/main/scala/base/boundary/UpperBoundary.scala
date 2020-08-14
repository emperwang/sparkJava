package base.boundary

/**
  * java中上界 <T extends A>   or  <? extends A>
  *
  * scala中的上界:  [T <: A]  [_ <: A]
  *
  */
object UpperBoundary {
  def main(args:Array[String]):Unit = {
    println(new CommonCompare(Integer.valueOf(5),Integer.valueOf(6)).greater)
    println(new CommonCompare(Double.box(5.0), Double.box(10)).greater)
    println(new CommonCompare(Long.box(10), Long.box(30)).greater)
  }

  // 编写一个通用方式来 进行基本类 比较大小
  class CommonCompare[T <: Comparable[T]](obj1:T, obj2:T){
      def greater = if (obj1.compareTo(obj2)> 0) obj1 else obj2
  }

  // 一般的比较方法
  def OldCompare(a:Int, b:Int):Int= {
    if(a> b) a  else b
  }

  def OldCompare1(a:Double, b:Double):Double= {
    if (a > b) a else b
  }
}





