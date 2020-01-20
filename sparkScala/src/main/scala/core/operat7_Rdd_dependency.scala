package core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * descripiton:
  *
  */
object operat7_Rdd_dependency {
  def main(args:Array[String]):Unit={
    val conf = new SparkConf().setMaster("local[2]").setAppName("dependency")
    val sc = new SparkContext(conf)
    val arr = Array(1,2,3,4,5,6,7,8,10)
    val arrRdd = sc.parallelize(arr)
    val distinctRDD = arrRdd.map(_*2).distinct()
    // 获取一个RDD的 依赖string
    val debugDependency = distinctRDD.toDebugString
    // 打印依赖
    println(debugDependency)
  }
}
