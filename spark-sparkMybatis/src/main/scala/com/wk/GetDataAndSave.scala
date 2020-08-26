package com.wk

import java.io.FileInputStream

import com.wk.Mapper.TestMapper
import com.wk.entity.TestBean
import com.wk.util.{FactoryUtil, SessionUtil}

object GetDataAndSave {
  def main(args: Array[String]): Unit = {
    // receive data and save to db
    if (args.length <= 0) {
      println("config file should be given.")
      System.exit(1)
    }
    val filePath = args(0)
    val inStream = new FileInputStream(filePath)
    val util = new FactoryUtil(inStream)
    val (ssc, dStream) = util.StreamingContext()

    dStream.foreachRDD(rdd => {
      rdd.foreach( rec => {
        println(s"receive topic: ${rec.topic()}, offset:${rec.offset()}," +
          s"value : ${rec.value()}, partition: ${rec.partition()}")
        val strings = rec.value().split(",")
        if (strings.length == 2 ){
          val bean = new TestBean()
          bean.setName(strings(0))
          bean.setDescription(strings(1))
          val session = SessionUtil.getSession()
          val mapper = session.getMapper(classOf[TestMapper])
          mapper.insertOne(bean)
          session.commit()
          session.close()
        }else {
          println(s"invalid value: ${rec.value()}")
        }
      })
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
