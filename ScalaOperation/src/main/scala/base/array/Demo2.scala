package base.array

import Array._

/**
  * 二位数据
  */
object Demo2 {
  def main(args: Array[String]): Unit = {
    var matrix = ofDim[Int](3,3)

    //创建矩阵
    for(i <- 0 to 2){
      for (j <- 0 to 2){
        matrix(i)(j) = j;
      }
    }

    //打印矩阵
    for(i <- 0 to 2){
      for(j <- 0 to 2){
        println(matrix(i)(j));
      }
    }
  }
}
