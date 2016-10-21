package com.jd.hyacinth.common.entity;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {
	private final JedisPool jedisPool;
	public RedisClient(String ip, int port){
		JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Constants.MAX_REDIS);
        config.setMinIdle(Constants.MAX_REDIS);
        config.setMaxTotal(Constants.MAX_REDIS);
        config.setFairness(true);
        config.setBlockWhenExhausted(true);

		jedisPool = new JedisPool(config,ip,port);
	}
	
	public Jedis getJedis() {
		return jedisPool.getResource();
	}
}
