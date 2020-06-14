package core.filecount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 计算文件的行数
  */
object FIleLines {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\hadoop_home")
    //定义conf,设置本地运行
    val conf = new SparkConf().setAppName("fileLines").setMaster("local")
    //设置sparkContext入口函数
    val sc = new SparkContext(conf)
    //读取本地文件
    val lines = sc.textFile("input")
    //把每行都映射为数字1,并计算总数,得到总行数
    val lineCount = lines.map(line=>1).reduce(_+_)
    //打印行数
    println("linesCount = "+lineCount)
  }
}
