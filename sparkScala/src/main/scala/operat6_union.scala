import org.apache.spark.{SparkConf, SparkContext}

/**
  * descripiton:
  * rdd 联合
  */
object operat6_union {
  def main(args:Array[String]):Unit={
    var conf = new SparkConf().setMaster("local[2]").setAppName("operat-union")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(Array(1,2,3,4,5,6))
    val rdd2 = sc.parallelize(Array(10,11,12,13,14,15))
    // union
    val unionRdd = rdd1.union(rdd2)

    unionRdd.collect().foreach(println)

    sc.stop()
  }
}
