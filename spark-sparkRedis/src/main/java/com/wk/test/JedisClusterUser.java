package com.wk.test;

import com.wk.util.JedisUtil;
import redis.clients.jedis.JedisCluster;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisClusterUser {
    private JedisCluster cluster;

    public JedisClusterUser() throws IOException {
        InputStream inputStream = new FileInputStream("F:\\github_code\\Mine\\sparkJava\\spark-sparkRedis\\src\\main\\resources\\kafkaConfig.properties");
        Properties prop = new Properties();
        prop.load(inputStream);
        cluster = JedisUtil.getCluster(prop);
    }

    public static void main(String[] args) throws IOException {
        JedisClusterUser user = new JedisClusterUser();
        user.SetStr();
    }

    public void SetStr() throws IOException {
        String key1 = "strkey1";
        cluster.set(key1, "this is key1 value");
        String s = cluster.get(key1);
        System.out.println("get value: " + s);
        cluster.expire(key1, 60*5);
    }

}
