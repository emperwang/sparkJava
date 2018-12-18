package com.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

import java.util.Arrays;
import java.util.List;

/**
 *  创建RDD的第二种方式
 *  并行化集合创建RDD
 */
public class ParallelizeCollection {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("parallelize").setMaster("local");
        //创建程序入口
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        //创建一个集合1
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //通过集合创建RDD
        JavaRDD<Integer> parallelizeRDD = sparkContext.parallelize(list);
        //计算集合中的值
        /**
         * 第一个第二个参数都是输入类型
         * 第三个参数是输出类型
         */
        Integer reduce = parallelizeRDD.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        //打印计算值
        System.out.println("1 to 9 累加和: " + reduce);
        //关闭javaSparkContext
        sparkContext.close();
    }
}
