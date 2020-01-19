import org.apache.spark.{SparkConf, SparkContext}

/**
  * descripiton:
  * map算子的功能是对每一个item进行操作
  * 转换操作
  */
object operat1_map {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("operat-map")
    val sc = new SparkContext(config = conf)
    /**
      *  需求: 在每一个item前添加一个 X
      */
    val listRdd = sc.parallelize(List("scala", "spark", "world"))
    val mapRdd = listRdd.map(("X"+_))

    mapRdd.collect().foreach(println)

  }
}
