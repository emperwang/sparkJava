package sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * descripiton:
  *  把 rdd 转换为 DataFrame
  */
object sparkSession2_rddToDF {
    def main(args:Array[String]):Unit = {
        val conf = new SparkConf().setMaster("local[2]").setAppName("rddToDF")

        val spark = SparkSession.builder().config(conf).getOrCreate()

        // 导入隐式转换的包
        import spark.implicits._

        // 创建rdd
        val listRDD = spark.sparkContext.parallelize(List(("zhangsan",20),("wangwu",50),("zhaosi",70)))
        // rdd转换为 dataframe
        val df = listRDD.toDF("name","age")
        // 把数据转换为临时表
        df.createTempView("su")
        // 使用 hql(hive  sql) 进行查询
        // spark.sql("select * from su").show()

        // 另外一种查询的方式
        df.select("name","age").show()

        spark.close()
    }
}
