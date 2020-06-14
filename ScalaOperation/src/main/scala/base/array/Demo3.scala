package base.array

import Array._
object Demo3 {
  def main(args: Array[String]): Unit = {
    var mylist1 = Array(1.9,2.9,3.4,3.5);

    var mulist2 = Array(8.9,7.9,0.4,1.5);

    var mylist3 = concat(mylist1,mulist2)

    for(x <- mylist3){
      println(x)
    }
  }
}
