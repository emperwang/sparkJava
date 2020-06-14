package core.filecount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 并行化创建RDD
  */
object ParallelizeRDD {
  def main(args:Array[String]):Unit={
    System.setProperty("hadoop.home.dir", "D:\\hadoop_home")
    //定义conf并设置本地运行模式
    val conf = new SparkConf().setAppName("parallize").setMaster("local")
    //定义入口函数
    val sc = new SparkContext(conf)
    //定义一个数组
    val numbers = Array(1,2,3,4,5,6,7,8,9)
    //并行化创建RDD
    val numRDD = sc.parallelize(numbers)
    //相加
    val sum = numRDD.reduce(_+_)
    //打印结果
    println("the sum is :"+sum)
  }
}
