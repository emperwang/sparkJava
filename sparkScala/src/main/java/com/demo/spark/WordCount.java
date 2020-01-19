package com.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * descripiton:
 *
 * @author: wk
 * @time: 16:04 2020/1/19
 * @modifier:
 */
public class WordCount {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("wordCount");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        List<String> list = Arrays.asList("hello", "hello", "world", "scala", "spark");
        JavaRDD<String> paralRDD = jsc.parallelize(list, 2);
        JavaPairRDD<String, Integer> pairRDD = paralRDD.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
            }
        });

        JavaPairRDD<String, Integer> reduceRDD = pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        reduceRDD.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> tup) throws Exception {
                System.out.println("key :"+tup._1+", value:"+tup._2);
            }
        });

        jsc.close();
    }
}
