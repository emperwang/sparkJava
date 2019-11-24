package com.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

/**
 * 计算本地文件的行数
 */
public class LocalFile {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("linesCount").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        //读取文件
        JavaRDD<String> linesRDD = sparkContext.textFile("E:\\code-workSpace\\testWord.txt");

        //把每行都映射为一个整数1  代表一行
        JavaRDD<Integer> linesCount = linesRDD.map(new Function<String, Integer>() {
            @Override
            public Integer call(String v1) throws Exception {
                return 1;
            }
        });
        //计算映射的总数
        Integer reduce = linesCount.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        System.out.println("文件的总行数: " + reduce);

        sparkContext.close();
    }
}
