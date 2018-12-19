package com.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

/**
 * 计算文本内单词总数
 */
public class LocalFileWordCount {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("fileWordCount").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> linesCount = sparkContext.textFile("E:\\code-workSpace\\testWord.txt");

        //计算出每行的单词数量
        JavaRDD<Integer> lineLengthRDD = linesCount.map(new Function<String, Integer>() {
            @Override
            public Integer call(String v1) throws Exception {
                return v1.split(" ").length;
            }
        });
        //把计算出的每行的单词数量求和,计算出总的单词数
        Integer reduce = lineLengthRDD.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        //打印总的单词数
        System.out.println("文件内单词总数: "+reduce);
        sparkContext.close();
    }
}
