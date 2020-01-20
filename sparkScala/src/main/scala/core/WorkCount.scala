package core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * descripiton:
  *
  */
object WorkCount {
  def main(args: Array[String]): Unit = {
    /**
      *  创建context
      */
    val conf = new SparkConf().setMaster("local[2]").setAppName("wordcount")
    val sc = new SparkContext(conf)

    /**
      * 创建RDD 的三种方式:
      * 1. 从外部文件
      * 2. makeRDD 从内存集合
      * 3. parallelize 从内存集合数据
      */
      // 第一个参数为:集合数据    第二个参数为: 分区数
    val arrRDD = sc.makeRDD(Array("hello word","hello scala","hello spark"),2)
    val parallelizeRDD = sc.parallelize(Array(1,2,3,4,5,6),2)

    val flatRdd = arrRDD.flatMap(x=>{x.split(" ")})
    flatRdd.collect().foreach(println)
    val mapRdd = flatRdd.map((_,1))
    val reduceRDD = mapRdd.reduceByKey(_+_)
    reduceRDD.collect().foreach(println)

    sc.stop()
  }
}
