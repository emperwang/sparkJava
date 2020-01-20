package sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

/**
  * descripiton:
  *  rdd -> dataFrame -> dataSet 之间的转换
  *
  *  dataSet->dataframe->rdd 之间的转换
  *
  *  rdd转换为 dataFrame
  *  rdd -> dataFrame  :  rdd.toDF
  *
  *   dataFrame转换为  rdd
  *  dataframe -> rdd : df.rdd
  *
  *   rdd 转换为dataset
  *  rdd -> dataSet   : rdd.toDS
  *
  *   dataSet转换为rdd
  *   DataSet -> rdd  : ds.rdd
  *
  *   dataframe转换为 dataSet
  *   dataFrame -> dataSet : df.to[Person]
  *
  *   dataSet转换为Dataframe
  *   dataSet -> dataframe  : ds.toDF
  *
  */
object sparkSession3_rddToDataSet {
    def main(args:Array[String]):Unit={
      val conf = new SparkConf().setMaster("local[2]").setAppName("rdd->dataFrame->dataSet")

      val spark = SparkSession.builder().config(conf).getOrCreate()

      // 导入隐式转换
      import spark.implicits._

      val listRdd: RDD[User] = spark.sparkContext.parallelize(List(User("zhangsan", 20), User("wangwu", 50),User("zhaosi", 70)))
      // rdd -> df
      val df1: DataFrame = listRdd.toDF()

      //  df -> rdd
      val rdd1: RDD[Row] = df1.rdd

        // rdd -> ds
      val ds: Dataset[User] = listRdd.toDS()

      ds.show()

      //  ds  -> rdd
      val rdd2: RDD[User] = ds.rdd

      // ds 转换为 df
      val df: DataFrame = ds.toDF("name","age")

      // df -> ds  ?

    }
}
// 模板类,用于模式匹配
case class User(name:String, age:Int)
