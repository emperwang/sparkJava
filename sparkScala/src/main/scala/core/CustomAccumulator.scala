package core

import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

object CustomAccumulator {
    def main(args:Array[String]):Unit = {
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("custom")
      val sc = new SparkContext(sparkConf)

      // 创建 rdd
      val dataRdd: RDD[String] = sc.makeRDD(List("Hadoop","world","Hello","super"))

      // 创建累加器  并进行注册
      val acccumulator = new WordAcccumulator
      sc.register(acccumulator)

      dataRdd.foreach({
        case str => acccumulator.add(str)
      })

      // 打印累加器
      println(acccumulator.value)

    }
}

/**
  *  自定义累加器
  *  AccumulatorV2[in, out] 两个泛型分别表示输入和输出
  */
class WordAcccumulator extends AccumulatorV2[String, util.ArrayList[String]]{
  private val list = new util.ArrayList[String]()

  // 判断初始的累加器是否是 0
  override def isZero: Boolean = {
    list.isEmpty
  }
  //  复制累加器
  override def copy(): AccumulatorV2[String, util.ArrayList[String]] = {
    new WordAcccumulator
  }

  // 复位累加器
  override def reset(): Unit = {
    list.clear()
  }

  // 向累加器中添加元素
  override def add(v: String): Unit = {
    // 添加包含 H 或 h 的单词
    if (v.contains("H") || v.contains("h")){
      list.add(v)
    }
  }

  // 合并累加器的值
  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = {
    list.addAll(other.value)
  }

  /**
    *  返回累加器的值
    * @return
    */
  override def value: util.ArrayList[String] = {
      list
  }
}
