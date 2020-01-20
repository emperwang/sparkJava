package sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * descripiton:
  *  dataFrame 的使用
  *  sparksession读取文件中的内容后就直接换转为 DataFrame 来进行操作了
  *  本例展示  dataFrame的基本使用
  */
object sparkSession1_dataFrame {
  def main(args:Array[String]):Unit={
      val conf : SparkConf = new SparkConf().setMaster("local[2]").setAppName("sparkSql-dataframe")

      val spark :SparkSession = SparkSession.builder().config(conf).getOrCreate()

      // 导入隐式转换, 也就是在  dataFrame  dataSet  rdd之间进行转换
      import spark.implicits._

    /**
      * 读取文件内容时, 方法有很多,具体可以从sparkShell或者源码中查看,  常用的方法是load, format来用指定数据的格式
      */
    // 此方法直接就是读取json格式的文件数据
      spark.read.json("F:\\github_code\\Mine\\sparkJava\\sparkScala\\in")
    // 直接读取文件,此读取就直接转换为了  DataFrame
      val frame :DataFrame = spark.read.format("json").load("F:\\github_code\\Mine\\sparkJava\\sparkScala\\in")

      // 把读取的数据转换为表
      frame.createTempView("user")
      spark.sql("select * from user").show()

      spark.close()
  }
}
