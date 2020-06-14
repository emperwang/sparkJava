package core.filecount

import org.apache.spark.{SparkConf, SparkContext}

object LocalWordCount {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\hadoop_home")
    //创建conf配置文件
    val conf = new SparkConf().setAppName("wordCount").setMaster("local")
    //创建入口函数
    val sc = new SparkContext(conf)
    //读取本地文件
    val linesRDD = sc.textFile("E:\\code-workSpace\\testWord.txt")
    //计算文件中的字数
    val count = linesRDD.flatMap(line => line.split(" ")).map(word => (word,1)).reduceByKey(_+_)
    //遍历打印文件字数
    count.foreach(word=>println(word._1 +" appeared "+ word._2+" times"))

  }
}
