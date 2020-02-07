package core

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 广播变量的使用
  */
object BroadCaseValue {
  def main(args: Array[String]): Unit = {
    val sparkconf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("broadcase")
    val sc = new SparkContext(sparkconf)

    val rdd1: RDD[(Int, String)] = sc.makeRDD(List((1, "a"), (2, "b"), (3, "c")))

    val lists = List((1, 1), (2, 2), (3, 3))

    // 创建广播变量
    val broadcase: Broadcast[List[(Int, Int)]] = sc.broadcast(lists)
    val resultRdd: RDD[(Int, (String, Any))] = rdd1.map({
      case (key, value) => {
        // 接收广播变量的值
        var v2: Any = null
        // 使用广播变量
        for (it <- broadcase.value) {
          if (key == it._1) {
            v2 = it._2
          }
        }
        // 返回值
        (key, (value, v2))
      }
    })
    // 打印最后的值
    resultRdd.foreach(println)
  }

}
