package com.wk.util;

import lombok.Getter;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Getter
public class JedisUtil {
    private static ThreadLocal<JedisCluster> local;

    public static JedisCluster getCluster(Properties prop) {
        JedisCluster cluster1 = local.get();
        if (cluster1 != null) {
            return cluster1;
        }
        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        String hoststr = prop.getProperty("redis.host");
        String[] iphs = hoststr.split(",");
        for (String iph : iphs) {
            String[] sp = iph.split(":");
            HostAndPort andPort = new HostAndPort(sp[0], Integer.parseInt(sp[1]));
            hostAndPortSet.add(andPort);
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大空闲数
        poolConfig.setMaxIdle(Integer.parseInt(prop.getProperty("redis.max.idle")));
        // 最大连接数
        poolConfig.setMaxTotal(Integer.parseInt(prop.getProperty("redis.max.total")));
        // 最小空闲数
        poolConfig.setMinIdle(Integer.parseInt(prop.getProperty("redis.min.idle")));
        // 获取连接时最大等待时间
        poolConfig.setMaxWaitMillis(Integer.parseInt(prop.getProperty("redis.max.wait.millis")));
        // 对拿到的连接进行校验
        poolConfig.setTestOnBorrow(true);
        JedisCluster cluster = new JedisCluster(hostAndPortSet, poolConfig);
        local.set(cluster);
        return cluster;
    }


}
