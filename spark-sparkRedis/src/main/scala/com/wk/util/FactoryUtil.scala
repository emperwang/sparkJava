package com.wk.util

import java.io.InputStream
import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

class FactoryUtil(prop: Properties) {

  def getKakfaparam(): Map[String,Object] ={
      val map = Map (
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> prop.getProperty("kafka.broker"),
        ConsumerConfig.GROUP_ID_CONFIG -> prop.getProperty("kakfa.groupid"),
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
        ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG -> "false"
      )
    map
  }

  def getStreamContext():(StreamingContext, InputDStream[ConsumerRecord[String,String]]) = {
      val topics = prop.getProperty("kafka.topic")
      val conf = new SparkConf().setAppName("redis-operator")
      val ssc = new StreamingContext(conf,Seconds(2))
      val kafakDStream = KafkaUtils.createDirectStream(ssc, LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set(topics), getKakfaparam()))
    (ssc, kafakDStream)
  }
}


object FactoryUtil {
  def apply(prop: Properties): FactoryUtil = new FactoryUtil(prop)
}