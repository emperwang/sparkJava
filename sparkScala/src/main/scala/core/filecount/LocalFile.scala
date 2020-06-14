package core.filecount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 计算一个文件中的总字数
  */
object LocalFile {
  def main(args:Array[String]):Unit={
    System.setProperty("hadoop.home.dir", "D:\\hadoop_home")
    //创建conf配置,并设置为本地运行
    val conf = new SparkConf().setAppName("localFile").setMaster("local")
    //创建sparkContext入口函数
    val sc = new SparkContext(conf)
    //读取文件
    val lineRDD = sc.textFile("E:\\code-workSpace\\testWord.txt")
    //计算总字数
    val wordCount = lineRDD.map(line => line.length).reduce(_+_)
    //打印
    println("total num is : "+wordCount)
  }
}
