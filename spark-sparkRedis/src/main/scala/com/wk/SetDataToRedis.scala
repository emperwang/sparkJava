package com.wk

import java.io.FileInputStream
import java.util.Properties

import com.wk.util.{FactoryUtil, JedisService, JedisUtil}
import org.apache.spark.TaskContext
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges}

object SetDataToRedis {
    def main(args:Array[String]):Unit = {
      if(args.length <= 0 ){
          println("Config file must be given.")
          System.exit(1)
      }
      val filepath = args(0)
      val inputStream = new FileInputStream(filepath)
      val prop = new Properties()
      prop.load(inputStream)
      val factoryUtil = FactoryUtil(prop)
      val (ssc, dstream) = factoryUtil.getStreamContext()
      // 把变量放到 算子的外面时,就需要序列化,不过此不支持序列化,就报错: org.apache.spark.SparkException: Task not serializable
      // 解决:
      // 1. 把方法放在算子内
      // 2. 把类实现 Serializable
      // 3. 把函数 修改为方法,来让高阶函数使用
      // 4. 修改为static方法,在每个executor中调用一次
      //val util = new JedisUtil(prop)

      dstream.foreachRDD( rdd => {
        val orangesArr = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
          rdd.foreachPartition(part => {
            val cluster = JedisUtil.getCluster(prop)
            val service = new JedisService(cluster)
            part.foreach( rec => {
              val partId = TaskContext.getPartitionId()
              val orange = orangesArr(partId)
              println(s"startoff: ${orange.fromOffset}, untilOff:${orange.untilOffset}")
              println(s"recive key: ${rec.key()}, offset:${rec.offset()}, topic:${rec.topic()}, values:${rec.value()}")
              val value = rec.value().split(",")
              // save to redis
              service.setStr(value(0), value(1))
            })
          })
        // commit offset
        dstream.asInstanceOf[CanCommitOffsets].commitAsync(orangesArr)
      })
      ssc.start()
      ssc.awaitTermination()
    }
}
