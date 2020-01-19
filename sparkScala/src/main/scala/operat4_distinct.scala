import org.apache.spark.{SparkConf, SparkContext}

/**
  * descripiton:
  * 去重操作, 此操作会进行 shuffle, 数据会打乱重新分区,速度慢
  */
object operat4_distinct {
  def main(args:Array[String]):Unit={
    var conf = new SparkConf().setMaster("local[2]").setAppName("operat-distinct")
    var sc = new SparkContext(conf)
    var arr = Array(1,2,3,4,5,6,7,8,1,2,3,4,5)
    var arrRDD = sc.parallelize(arr,3)
    // 去重操作，参数为  分区数
    var distinctRDD = arrRDD.distinct(2)
    distinctRDD.collect().foreach(println)

  }
}
