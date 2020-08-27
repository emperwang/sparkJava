package com.wk.util;

import redis.clients.jedis.JedisCluster;

public class JedisService {

    private JedisCluster cluster;
    public JedisService(JedisCluster cluster){
        this.cluster = cluster;
    }


    public void setStr(String key, String val){
        cluster.set(key, val);
        cluster.expire(key, 3600*2);
    }
}
