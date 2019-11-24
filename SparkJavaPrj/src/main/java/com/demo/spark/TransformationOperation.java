package com.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * transformation 操作
 */
public class TransformationOperation {
    public static void main(String[] args) {
        //map();
        //filter();
        //flatMap();
        //groupByKey();
        //reduceByKey();
        //sortByKey();
        //join();
        cogroup();
    }

    /**
     * 使用并行创建rdd,并让其中的值都乘以2
     */
    private static void map(){
        //创建sparkconf并设置为本地运行
        SparkConf sparkConf = new SparkConf().setAppName("map Transformation").setMaster("local");
        // 创建入口sparkContext
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        //创建list
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //并行化创建rdd
        JavaRDD<Integer> listRDD = sparkContext.parallelize(list);
        //让rdd中的值乘以2
        // 第一个参数为输入类型,
        // 第二个参数为输出类型
        JavaRDD<Integer> results = listRDD.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {
                return v1 * 2;
            }
        });
        //遍历结果
        results.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer integer) throws Exception {
                System.out.println("value is :"+integer);
            }
        });
        sparkContext.close();
    }

    /**
     * 过滤算子
     */
    private static void filter(){
        SparkConf sparkConf = new SparkConf().setAppName("filter demo").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //并行化创建rdd
        JavaRDD<Integer> parallelize = sparkContext.parallelize(list);
        //过滤出偶数
        JavaRDD<Integer> filter = parallelize.filter(new Function<Integer, Boolean>() {
            @Override
            public Boolean call(Integer v1) throws Exception {
                return v1 % 2 == 0 ;
            }
        });
        //遍历所有
        filter.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer integer) throws Exception {
                System.out.println("value is: "+integer);
            }
        });

        sparkContext.close();
    }

    /**
     * 把每行字符串拆分成一个一个的单词
     */
    private static void flatMap(){
        //创建sparkConf并这是本地运行模式
        SparkConf sparkConf = new SparkConf().setAppName("flatMap").setMaster("local");
        //创建spark入口
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        //模拟创建字符串list
        List<String> list = Arrays.asList("hello world", "hello hadoop", "hello spark");
        //并行化创建RDD
        JavaRDD<String> parallelize = sparkContext.parallelize(list);
        //根据空格把每行数据拆分成单个单词
        JavaRDD<String> flatMapRDD = parallelize.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")).iterator();
            }
        });
        //遍历每个单词
        flatMapRDD.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println("value is:"+s);
            }
        });

        sparkContext.close();
    }

    /**
     * 通过key进行聚合操作
     */
    private static void groupByKey(){
        SparkConf sparkConf = new SparkConf().setAppName("groupByKey").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        //模拟集合
        List<Tuple2<String, Integer>> list = Arrays.asList(
                new Tuple2<String, Integer>("class1", 80),
                new Tuple2<String, Integer>("class2", 70),
                new Tuple2<String, Integer>("class2", 60),
                new Tuple2<String, Integer>("class3", 90),
                new Tuple2<String, Integer>("class1", 85)
        );


        JavaPairRDD<String, Integer> pairRDD = sparkContext.parallelizePairs(list);
        //通过key进行聚合操作
        JavaPairRDD<String, Iterable<Integer>> groupBy = pairRDD.groupByKey();

        groupBy.foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
            @Override
            public void call(Tuple2<String, Iterable<Integer>> value) throws Exception {
                System.out.println("key == "+value._1);
                Iterator<Integer> iterator = value._2.iterator();
                while (iterator.hasNext()){
                    System.out.println(iterator.next());
                }
                System.out.println("=====================");
            }
        });

        sparkContext.close();
    }

    /**
     * 根据key进行聚合操作
     */
    private static void reduceByKey(){
        SparkConf sparkConf = new SparkConf().setAppName("reduceByKey").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        List<Tuple2<String, Integer>> list = Arrays.asList(new Tuple2<String, Integer>("class1", 2),
                new Tuple2<String, Integer>("class2", 8),
                new Tuple2<String, Integer>("class2", 9),
                new Tuple2<String, Integer>("class1", 2));
        //并行化解析tuple创建JavaPair
        JavaPairRDD<String, Integer> pairRDD = sparkContext.parallelizePairs(list);
        /**
         * 这里的reduceByKey传入的参数是function2，其有三个泛型参数
         * 第一个 和第二个参数 都表示原始的RDD中的value值
         * 第三个表示：传出的值
         */
        JavaPairRDD<String, Integer> results = pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        results.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> value) throws Exception {
                System.out.println("key == "+value._1+" value:"+value._2);
            }
        });

        sparkContext.close();
    }

    /**
     * 通过key进行排序
     */
    public static void sortByKey(){
        SparkConf sparkConf = new SparkConf().setAppName("sortByKey").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        List<Tuple2<Integer, String>> list = Arrays.asList(
                new Tuple2<Integer, String>(65, "leo"),
                new Tuple2<Integer, String>(85, "tom"),
                new Tuple2<Integer, String>(75, "mike"),
                new Tuple2<Integer, String>(90, "jack")
        );
        JavaPairRDD<Integer, String> pairRDD = sparkContext.parallelizePairs(list);
        //根据key进行排序，默认是升序：true;  false就是降序
        JavaPairRDD<Integer, String> sortRDD = pairRDD.sortByKey(false);

        sortRDD.foreach(new VoidFunction<Tuple2<Integer, String>>() {
            @Override
            public void call(Tuple2<Integer, String> value) throws Exception {
                System.out.println("key : "+value._1+" value:"+value._2);
            }
        });
        sparkContext.close();
    }

    /**
     * join算子，类似说sql双表联查
     */
    private static void join(){
        SparkConf sparkConf = new SparkConf().setAppName("join").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        //学生集合
        List<Tuple2<Integer, String>> students = Arrays.asList(
                new Tuple2<Integer, String>(1, "leo"),
                new Tuple2<Integer, String>(2, "jack"),
                new Tuple2<Integer, String>(3, "mike")
        );
        //学生分数集合
        List<Tuple2<Integer, Integer>> scores = Arrays.asList(
                new Tuple2<Integer, Integer>(1, 100),
                new Tuple2<Integer, Integer>(2, 80),
                new Tuple2<Integer, Integer>(3, 50)
        );
        //并行化创建RDD
        JavaPairRDD<Integer, String> studRDD = sparkContext.parallelizePairs(students);
        JavaPairRDD<Integer, Integer> scoresRDD = sparkContext.parallelizePairs(scores);
        /**
         * 使用join算子,关联两个rdd
         *  join以后,还是会根据key进行join,并返回JavaPairRDD
         *  返回的JavaPairRDD第一个参数是: 之前两个RDD的key值,因为根据key关联的
         *  第二个参数tuple2,分别是之前两个RDD的value值
         *  如: (1,2),(1,3),(1,4)和(2,7),(2,8),(2,9)join的话,结果就是
         *     (1,(2,7)),(1,(2,8)),(1,(2,9))
         */
        JavaPairRDD<Integer, Tuple2<String, Integer>> join = studRDD.join(scoresRDD);

        join.foreach(new VoidFunction<Tuple2<Integer, Tuple2<String, Integer>>>() {
            @Override
            public void call(Tuple2<Integer, Tuple2<String, Integer>> value) throws Exception {
                System.out.println("student id:"+value._1);
                System.out.println("student name:"+value._2._1);
                System.out.println("student score:"+value._2._2);
            }
        });
        sparkContext.close();
    }

    private static void cogroup(){
        SparkConf sparkConf = new SparkConf().setAppName("cogroup").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        List<Tuple2<Integer, String>> students = Arrays.asList(
                new Tuple2<Integer, String>(1, "leo"),
                new Tuple2<Integer, String>(2, "lua"),
                new Tuple2<Integer, String>(3, "mike")
        );
        List<Tuple2<Integer, Integer>> scores = Arrays.asList(
                new Tuple2<Integer, Integer>(1, 100),
                new Tuple2<Integer, Integer>(2, 90),
                new Tuple2<Integer, Integer>(3, 100),
                new Tuple2<Integer, Integer>(1, 90),
                new Tuple2<Integer, Integer>(2, 100),
                new Tuple2<Integer, Integer>(3, 50)
        );

        JavaPairRDD<Integer, String> stuRDD = sparkContext.parallelizePairs(students);
        JavaPairRDD<Integer, Integer> scoreRDD = sparkContext.parallelizePairs(scores);

        JavaPairRDD<Integer, Tuple2<Iterable<String>, Iterable<Integer>>> cogroup =
                stuRDD.cogroup(scoreRDD);

        cogroup.foreach(new VoidFunction<Tuple2<Integer, Tuple2<Iterable<String>, Iterable<Integer>>>>() {
            @Override
            public void call(Tuple2<Integer, Tuple2<Iterable<String>, Iterable<Integer>>> value) throws Exception {
                System.out.println("studnet id:"+value._1);
                System.out.println("studnet name:"+value._2._1);
                System.out.println("studnet value:"+value._2._2);
            }
        });

        sparkContext.close();
    }


}
