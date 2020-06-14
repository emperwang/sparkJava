package base.array

import Array._
object Demo4 {
  def main(args: Array[String]): Unit = {
    var mylist1 = range(10,20,2)
    var mylist2 = range(10,20)

    //输出所有元素
    for(x <- mylist1){
      print(x)
    }
    println()

    for(x <- mylist2){
      print(x)
    }
  }
}
