package com.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 本地运行的wordcount程序
 */
public class WordCountLocal {
    public static void main(String[] args) {
        //为hadoop设置家目录
        //System.setProperty("hadoop.home.dir","D:\\hadoop_home\\hadoop-common-2.2.0-bin-master");
        /** 第一步:创建sparkconf对象
         * 创建spark的配置,设置先web显示的app名字,
         * setMaster() 可以设置spark应用程序要连接的spark集群的master的url
         * 如果设置为local,则表示为本地运行
         */
        SparkConf sparkConf = new SparkConf().setAppName("wordCountLocal").setMaster("local");
        /**第二步: 创建javaSparkContext对象
         * 在spark中,sparkContext是spark所有功能的一个入口,你无论是使用java,scala
         * python都必须要有一个SparkContext,它的主要作用,包括初始化spark应用程序
         * 所需的一些核心组件,包括调度器(DAGSchedule,TaskScheduler) 还会去到spark Master
         * 节点上进行注册,等等
         * 但是在spark中,编写不同类型的spark程序,使用的sparkContext是不同的,scala就是使用
         * 原生的sparkContext对象
         * 使用java,那么就是javaSparkContext对象
         * 如果是开发spark SQL程序, 那么就是SQLContext,HiveContext
         * 如果是spark Streaming程序, 那么它独有的sparkContext
         */
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        /**第三步: 针对输入源(hdfs文件,本地文件,等等) 创建一个初始RDD
         * 输入源中的数据会打散,分配到RDD的每个partition中,从而形成一个初始的分布式
         * 数据集. 我们这里呢,因为是本地测试,所以呢,就是针对本地文件
         * sparkContext中,用于根据文件类型的输入源出创建RDD的方法,叫做textFile()方法
         * 在java中,创建的普通RDD,都叫做javaRDD
         * 在这里呢,RDD中,有元素这种概念, 如果是hdfs或者本地文件,创建的RDD,每一个元素
         * 就相当于是文件中的一行
         */
        JavaRDD<String> linesRDD = sparkContext.textFile("E:\\code-workSpace\\testWord.txt");
        /**
         * 第四步: 对初始RDD进行transformation操作,也就是一些计算操作
         * 通常操作会通过创建function,并配合RDD的map,flatMap等算子来执行
         * function,通常,如果比较简单,则创建指定function的匿名内部类
         * 但是如果function比较复杂,则会单独创建一个类,作为实现这个接口的类
         *
         */

        /**
         * 将每一行拆分成单个的单词
         * flatMapFunction,有两个泛型参数,分别代表了输入和输出的类型
         * 我们这里呢,输入肯定是string,因为是一行一行的文本,输出,其实也是string
         * 因为是每一行的单词
         * flatMap算子的作用: 就是将RDD的一个元素,拆分成一个或多个元素
         */
        JavaRDD<String> words = linesRDD.flatMap(new FlatMapFunction<String, String>() {

            @Override
            public Iterator<String> call(String s) throws Exception {
                return  Arrays.asList(s.split(" ")).iterator();
            }
        });

        /**
         * 接着,需要将每一个单词,映射为(单词,1)这种格式
         * 因为只有这样,后面才能根据单词作为key,来进行每个单词的出现次数的累加
         * mapToPair 其实就是将每个元素,映射为一个(v1,v2)这样的Tuple2类型的元素
         * 如果大家还记得scala里面讲的tuple,那么没错,这里的tuple2就是scala类型
         * 包含了两个值
         * MapToPair这个算子要求是与PairFunction配合使用,
         * 第一个参数:代表了输入类型
         * 第二个参数:代表了Tuple2的第一个值
         * 第三个参数:带包了Tuple2的第二个值
         * JavaPairRdd的两个泛型参数,分别代表了tuple元素的第一个值和第二个值
         */
        JavaPairRDD<String, Integer> pairRDD = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word, 1);
            }
        });
        /**
         * 这里使用reduceByKey算子,对每个key对应的value,都进行reduce操作
         * 比如javaPairRDD中有几个元素,分别为(hello,1)(hello,1)(hello,1)
         * reuce操作,相当于把第一个值个第二个值进行计算,然后再将结果与第三个值进行计算
         * 比如这个hello,就相当于是1+1=2,然后2+1=3
         * 最后返回的javaPairRDD中的元素,也就是tuple,但是第一个值就是每个key,第二个值就是key
         * 的value就是reduce之后的结果,相当于是每个单词出现额次数
         */
        JavaPairRDD<String, Integer> wordsCount = pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        /**
         * 到这里,我们已经通过spark的算子,统计出了单词的次数
         * 但是,之前我们使用的flatMap,mapToPair,reduceByKey这种操作
         * 都叫做transformation操作
         * 一个spark应用中,光是有transformation操作,是不行的,是不会执行的
         * 必须要有一种叫做action的操作,
         * 最后,使用一种叫做action的操作,这里使用foreach,来触发程序的运行
         */
        wordsCount.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> wordCount) throws Exception {
                System.out.println(wordCount._1+" appeared "+wordCount._2+" times");
            }
        });

        sparkContext.close();
    }
}
