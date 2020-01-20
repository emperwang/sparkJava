package core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * descripiton:
  *
  */
object operat3_filter {
  def main(args:Array[String]):Unit={
    var conf = new SparkConf().setMaster("local[2]").setAppName("operat_filter")
    var sc = new SparkContext(conf)
    var arr = Array(1,2,3,4,5,6,7,8)
    var lt = List(1 to 10)    // 此过滤如何使用
    lt.foreach(println)
    val listRDD = sc.parallelize(arr)
    // listRDD.flatMap(x=>x).collect().foreach(println)
    // 过滤偶数
    var filterRDD = listRDD.filter((_%2==0))
    filterRDD.collect().foreach(println)
  }
}
