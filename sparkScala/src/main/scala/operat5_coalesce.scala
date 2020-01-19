import org.apache.spark.{SparkConf, SparkContext}

/**
  * descripiton:
  * 分区数合并
  */
object operat5_coalesce {
  def  main(args:Array[String]):Unit={
    var conf = new SparkConf().setMaster("local[2]").setAppName("operat-coalEsac")
    var sc = new SparkContext(conf)
    var lt = List(1,2,3,4,5,6,7,8,10)
    // lt.foreach(println)

    var gen = List(1 to 10)
    // gen.foreach(println)
    var listRdd = sc.parallelize(lt,5)
    // 打印rdd的分区数
    listRdd.partitions.foreach(part=>println(part.index))

    /**
      *  coalesce: 分区合并.第一个参数为 修改后的分区，  第二个参数: 是否进行shuffle
      */
    var coalRdd = listRdd.coalesce(3)
    val glomRdd = coalRdd.glom()
    glomRdd.collect().foreach(d => println(d.toString))
  }
}
