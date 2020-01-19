import org.apache.spark.{SparkConf, SparkContext}

/**
  * descripiton:
  */
object operat8_partitions_num {
  def main(args:Array[String]):Unit={
    val conf = new SparkConf().setMaster("local[2]").setAppName("operat-partitionsNum")
    val sc = new SparkContext(conf)
    var arr = Array(1,2,3,4,5,6,7,8,9,10)

    val arrRDD = sc.parallelize(arr,5)
    // 打印分区数
    arrRDD.partitions.foreach(part=>println(part.index))
  }
}
