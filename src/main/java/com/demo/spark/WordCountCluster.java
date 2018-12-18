package com.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 集群运行的wordCount程序
 */
public class WordCountCluster {
    public static void main(String[] args) {
        //创建sparkConf，并制定appName
        SparkConf sparkConf = new SparkConf().setAppName("cluster wordCount");
        //创建程序入口
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        //读取hdfs上的文件
        JavaRDD<String> lines = sparkContext.textFile("hdfs://name1:9000/spark.txt");
        //把每行拆分成单词
        JavaRDD<String> wordRDD = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")).iterator();
            }
        });
        // 把每个单词映射成tuple格式
        JavaPairRDD<String, Integer> pairRDD = wordRDD.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
            }
        });
        //通过reduce操作,算出结果
        JavaPairRDD<String, Integer> result = pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        //调用一个action操作,计算结果
        result.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> word) throws Exception {
                System.out.println(word._1 +" appeared "+word._2 + " times");
            }
        });
    }
}
