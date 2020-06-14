package core.filecount

import org.apache.spark.{SparkConf, SparkContext}

object SparkDemo11 {
  def main(args: Array[String]):Unit = {
    System.setProperty("hadoop.home.dir", "D:\\hadoop_home")
    val logFile = "hdfs://192.168.72.11:9000/wordCount.txt"
    val sparkConf = new SparkConf().setAppName("wordCount").setMaster("local")
    val sc = new SparkContext(sparkConf)
    val count = sc.textFile(logFile).flatMap(line => line.split(" ")).map(word => (word,1)).reduceByKey(_+_)
    count.saveAsTextFile("hdfs://192.168.72.11:9000/sparkOutPut")
    sc.stop()
  }
}
