import org.apache.spark.{SparkConf, SparkContext}

/**
  * descripiton:
  * flatMap 扁平化处理,
  */
object operat2_flatmap {
  def main(args:Array[String]):Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("operat-flatMap")
    val sc = new SparkContext(conf)

    val listRdd = sc.parallelize(List("hello world", "spark fast"))
    val flatRdd = listRdd.flatMap((_.split(" ")))

    flatRdd.collect().foreach(println)


    sc.stop()
  }
}
