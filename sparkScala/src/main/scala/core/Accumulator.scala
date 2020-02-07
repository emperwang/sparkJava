package core

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

/**
  *   spark内部有三种数据类型：RDD    累加器   广播变量
  *  使用内置的类加器
  */
object Accumulator {
  def main(args: Array[String]): Unit = {
      val sparkconf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("accumulator")
      val sc = new SparkContext(sparkconf)
      // 创建一个RDD
      val dataRdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))

      // reduce 求和
      val i: Int = dataRdd.reduce(_+_)
      println(i)

      // 使用累加器求和
      val sum: LongAccumulator = sc.longAccumulator("sum")
      dataRdd.foreach({
        case  i => sum.add(i)
      })

      println("accumulator sum :",sum)






  }
}
