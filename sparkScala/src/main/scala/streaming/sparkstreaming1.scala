package streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * descripiton:
  * 使用socket接收远程主机(使用nc -kl 9999)的某个端口发送的数据,并进行wordCount
  */
object sparkstreaming1 {
    def main(args:Array[String]):Unit = {
      val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("socketWordCount")

      val streamingContext = new StreamingContext(conf,Seconds(10))

      val sockDstream: ReceiverInputDStream[String] = streamingContext.socketTextStream("192.168.72.18",9999)

      // 接收数据并进行转换
      val wordDstream: DStream[(String, Int)] = sockDstream.flatMap(_.split(" ")).map((_,1)).reduceByKey((_+_))

      wordDstream.print()

      // 接收器开始
      streamingContext.start()
      // 等待任务停止
      streamingContext.awaitTermination()
    }
}
