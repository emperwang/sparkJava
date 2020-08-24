package com.wk

import java.io.FileInputStream
import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies, LocationStrategy}
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}

object MainStarter {
   def main(args: Array[String]) :Unit ={
     val configfile = args(0)
     println("config file=", configfile)
     val inputStream = new FileInputStream(configfile)
     val properties = new Properties()
     properties.load(inputStream)
     val names = properties.propertyNames()
     while (names.hasMoreElements){
        val name = names.nextElement()
       val value = properties.get(name)
       println(s"name=${name}, vlues=${value}")
     }
     val config = new KafkaConfig(properties)
     val conf = new SparkConf().setAppName("kafka-operator").setMaster(properties.getProperty("spark.master"))
     val sc = new StreamingContext(conf, Seconds(2))
     val kafkaDstream = KafkaUtils.createDirectStream(sc, LocationStrategies.PreferConsistent,
       ConsumerStrategies.Subscribe[String,String](config.getTopic(), config.getConfig()))

     kafkaDstream.foreachRDD( rdd => {
        rdd.foreach( record => {
          val offset = record.offset()
          val topic = record.topic()
          val value = record.value()
          println(s"consumer offset: ${offset}, topic:${topic}, vlaue:${value}")

        })
     })

     sc.start()
     sc.awaitTermination()
   }


  class KafkaConfig(prop: Properties) {

    def getTopic(): Set[java.lang.String] = {
      val ss = Set(prop.getProperty("kafka.topic"))
      println("topics:=", ss.toString())
      ss
    }

    def getConfig(): Map[String,Object] = {
      val param = Map(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> prop.getProperty("kafka.broker"),
                      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
                      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
                      ConsumerConfig.GROUP_ID_CONFIG-> "driver-group-id"
                    )
      println("maps = ", param.toString())
      param
    }
  }
}
