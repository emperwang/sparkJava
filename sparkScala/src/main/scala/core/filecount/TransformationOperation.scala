package core.filecount

import org.apache.spark.sql.catalyst.expressions.ArrayUnion
import org.apache.spark.{SparkConf, SparkContext}

object TransformationOperation {
  def main(args:Array[String]):Unit={
    System.setProperty("hadoop.home.dir", "D:\\hadoop_home")
    //map()
    //filter()
    //flatMap()
    //groupByKey()
    //reduceByKey()
    //sortByKey()
    //join()
    coGroup()
  }

  def coGroup(): Unit ={
    val conf = new SparkConf().setAppName("cogroup").setMaster("local")
    val sc = new SparkContext(conf)
    val score = Array(
      new Tuple2(1,"20"),
      new Tuple2(2,"40")
    )
    val name = Array(
      new Tuple2(1,"mike"),
      new Tuple2(2,"lufei")
    )

    val scoreRDD = sc.parallelize(score)
    val nameRDD = sc.parallelize(name)

    val coGroup = nameRDD.cogroup(scoreRDD)
    //(int,(iterable(String),iterable(string)))
    coGroup.foreach(num =>{
      println("id : "+num._1)
      num._2._1.foreach(str=>println("string is:"+str))
      num._2._2.foreach(num => println("num is:"+num))
      println("-------------------------------")
    })
  }

  def join(): Unit ={
    val conf = new SparkConf().setAppName("join").setMaster("local")
    val sc = new SparkContext(conf)
    val score = Array(
      new Tuple2(1,"20"),
      new Tuple2(2,"40"),
      new Tuple2(3,"50"),
      new Tuple2(4,"60")
    )
    val name = Array(
      new Tuple2(1,"mike"),
      new Tuple2(2,"lufei"),
      new Tuple2(3,"peter"),
      new Tuple2(4,"sufei")
    )

    val scorePair = sc.parallelize(score)
    val namePair = sc.parallelize(name)

    val nameScore = namePair.join(scorePair)

    nameScore.foreach(nm => {
      println("id :"+nm._1)
      println("name :"+nm._2._1)
      println("score :"+nm._2._2)
      println("-------------------------------")
    })
  }

  def sortByKey(): Unit ={
    val conf = new SparkConf().setAppName("sortByKey").setMaster("local")
    val sc = new SparkContext(conf)
    val scoreList = Array(
      new Tuple2(10,"leo"),
      new Tuple2(20,"mike"),
      new Tuple2(30,"sufff")
    )

    val pairRDD = sc.parallelize(scoreList)
    val sortRDD = pairRDD.sortByKey()
    sortRDD.foreach(num => println("num :"+num._1 +" name :"+num._2))
  }

  def reduceByKey(): Unit ={
    val conf = new SparkConf().setAppName("reduceByKey").setMaster("local")
    val sc = new SparkContext(conf)
    val scoresList = Array(
      new Tuple2("class1",90),
      new Tuple2("class1",90),
      new Tuple2("class2",10),
      new Tuple2("class2",10)
    )
    //并行化创建RDD
    val scoreRDD = sc.parallelize(scoresList)
    //根据key进行聚合操作
    val reduceCount = scoreRDD.reduceByKey((a,b)=>a+b)
    //遍历打印成员值
    reduceCount.foreach(score => {
      println("class = "+score._1+" score :"+score._2)
    })
  }

  def groupByKey(): Unit ={
    val conf = new SparkConf().setAppName("groupByKey").setMaster("local")
    val sc = new SparkContext(conf)
    //模拟数组
    val scoreList = Array(
      new Tuple2("class1",80),
      new Tuple2("class1",60),
      new Tuple2("class2",90),
      new Tuple2("class3",85)
    )
    //并行化创建RDD 这个RDD有些不一样  好好看看
    val scores = sc.parallelize(scoreList,1)
    //按照key进行分组
    val groupRDD = scores.groupByKey()
    //遍历分组后的成员值
    groupRDD.foreach(score => {
      println("calss = "+score._1)
      score._2.foreach(num => println("score is:"+num))
      println("-------------------------------------------")
      }
    )
  }

  def flatMap(): Unit ={
    val conf = new SparkConf().setAppName("flatMap").setMaster("local")
    val sc = new SparkContext(conf)
    //创建字符串数组
    val num = Array("hello you","hello me","hello everyone")
    //并行化创建RDD,分区为1
    val lineRDD = sc.parallelize(num,1)
    //把每行字符都按照空格进行拆分
    val wordRDD = lineRDD.flatMap(line => line.split(" "))
    //打印
    wordRDD.foreach(word => println("word is="+word))
  }

  def map(): Unit ={
    val conf = new SparkConf().setAppName("map").setMaster("local")
    val sc = new SparkContext(conf)
    //创建数组
    val number = Array(1,2,3,4,5)
    //并行化创建RDD
    val numRDD = sc.parallelize(number,1)
    //把数组中的值都变成平方值
    val douNum = numRDD.map(num => num * num)
    //打印
    douNum.foreach( num => println("num is:"+num))
  }

  def filter(): Unit ={
    val conf = new SparkConf().setAppName("filter").setMaster("local")
    val sc = new SparkContext(conf)
    val number = Array(1,2,3,4,5,6,7,8,9)
    val numRDD = sc.parallelize(number,1)
    //过滤出来偶数
    val evenRDD = numRDD.filter(num => num % 2 ==0)
    //遍历打印
    evenRDD.foreach(num => println("num = "+num))
  }
}
