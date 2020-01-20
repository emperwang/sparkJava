package streaming

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, DefaultPerPartitionConfig, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * descripiton:
  *
  */
object sparkStream2_kafka {
    def main(args:Array[String]):Unit={

      val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("streaming-kafka")

      val streamingContext = new StreamingContext(conf,Seconds(5))

      val brokers = "192.168.72.18:9092, 192.168.72.11:9092"
      val groupId = "streaming"
      val topicName = Set("test")
      val maxPoll = 200

      val params: Map[String, Object] = Map(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers,
        ConsumerConfig.GROUP_ID_CONFIG -> groupId,
        ConsumerConfig.MAX_POLL_RECORDS_CONFIG -> maxPoll.toString,
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer]
      )

      val kafkaStreamDstream: InputDStream[ConsumerRecord[String,String]] = KafkaUtils.createDirectStream(streamingContext, LocationStrategies.PreferConsistent,
        ConsumerStrategies.Subscribe(topicName, params))

      // 消费kafka的数据并且 统计每个批次的字数
      kafkaStreamDstream.flatMap((_.value().split(" "))).map((_,1)).reduceByKey((_+_)).print()

      streamingContext.start()
      streamingContext.awaitTermination()
    }
}
