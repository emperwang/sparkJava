package com.demo.spark;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.serializer.KryoSerializer;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SparkDirectKafka {
    private String kafkaAddr="192.168.72.18:9092,192.168.72.15:9092,192.168.72.11:9092";
    private String topics="test";
    private String groupId = "test-group";

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("hadoop.home.dir","F:\\hadoop-2.7.1");
        SparkDirectKafka directKafka = new SparkDirectKafka();
        JavaStreamingContext streamingContext1 = directKafka.getStreamingContext();
        JavaInputDStream<ConsumerRecord<String, String>> dstream = directKafka.getJavaInputDstream(streamingContext1);
        JavaDStream<ConsumerRecord<String, String>> filter = dstream.filter(record -> {
            System.out.println(record.topic());
            System.out.println(record.key());
            System.out.println(record.value());
            return false;
        });
        filter.print();
        streamingContext1.start();
        streamingContext1.awaitTermination();
    }

    public JavaInputDStream<ConsumerRecord<String,String>> getJavaInputDstream(JavaStreamingContext context){
        Set<String> topicSet = topicSet();
        Map<String, Object> kakfaParam = kakfaParam();
        JavaStreamingContext javaStreamingContext = context;
        JavaInputDStream<ConsumerRecord<String,String>> directStream = KafkaUtils.createDirectStream(javaStreamingContext, LocationStrategies.PreferConsistent(),
                ConsumerStrategies.Subscribe(topicSet, kakfaParam));

        return directStream;
    }

    public JavaStreamingContext getStreamingContext(){
        SparkConf sparkConf = new SparkConf().setAppName("kafak-direct");
        sparkConf.set("spark.streaming.kafka.maxRatePerPartition","100");
        sparkConf.set("spark.serializer", KryoSerializer.class.getName());
        //sparkConf.set("spark.driver.allowMultipleContexts","true");
        sparkConf.setMaster("local[*]");
        //JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        //javaSparkContext.setLogLevel("WARN");
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf, new Duration(2000));
        return javaStreamingContext;
    }

    public Set<String> topicSet(){
        Set<String> topicSets = new HashSet<>();
        String[] split = topics.split(",");
        for (String s : split) {
            topicSets.add(s);
        }
        return topicSets;
    }

    public Map<String,Object> kakfaParam(){
        Map<String,Object> kafkaConsumerParam = new HashMap<>();
        kafkaConsumerParam.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaAddr);
        kafkaConsumerParam.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaConsumerParam.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        kafkaConsumerParam.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        kafkaConsumerParam.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        kafkaConsumerParam.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        return kafkaConsumerParam;
    }
}
