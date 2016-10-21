package com.jd.hyacinth.common.tools;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.jd.common.util.StringUtils;
import com.jd.hyacinth.common.entity.RedisClient;

/**
 * 常用数据缓存
 * @author baironglin
 *
 */
@Component("dataCacheUtils")
public class DataCacheUtils {
	@Resource
	private RedisClient redisClient;
	

	private Logger log = Logger.getLogger(getClass());
	
	/** 日期时间格式化串 */
	public final static String DATE_FORMAT_STR = "yyyy-MM-dd_HH:mm:ss";
	
	/**
	 * 设置一个时间对象
	 * @param key 该对象在缓存中的key
	 * @param date 要设置的对象
	 */
	public void setDate(String key, Date date) {
		set(key, new SimpleDateFormat(DATE_FORMAT_STR).format(date));
	}
	
	/**
	 * 获取一个时间对象
	 * @param key 该对象在缓存中的key
	 * @return 缓存中对应的时间对象
	 * @throws Exception
	 */
	public Date getDate(String key) throws Exception {
		return new SimpleDateFormat(DATE_FORMAT_STR).parse(get(key));
	}
	
	/**
	 * 获取一个时间对象
	 * @param key 该对象在缓存中的key
	 * @param defaultValue 出错时默认时间
	 * @return 缓存中对应的时间对象
	 */
	public Date getDate(String key, Date defaultValue) throws Exception {
		try {
			return getDate(key);
		} catch (Exception e) {
			log.info("#cache_error 缓存 key： " + key + " 读取错误，使用默认值： " + defaultValue, e);
			return defaultValue;
		}
	}
	
	/**
	 * 得到整型的缓存值
	 * @param key 要获取的key
	 * @return 整型值
	 */
	public long getLong(String key) {
		return Long.parseLong(get(key));
	}
	
	/**
	 * 得到整型的缓存值
	 * @param key 要获取的key
	 * @param defaultValue 为空 或出错时的默认值
	 * @return 整型值
	 */
	public long getLong(String key, long defaultValue) {
		String str = get(key);
		if (str == null) {
			return defaultValue;
		}
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	
	
	/**
	 * 设置一个对象
	 * @param key
	 * @param obj
	 */
	public void setObj(String key, Object value) {
		try {
			redisClient.getJedis().set(key, JSON.toJSONString(value));
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", value=" + JSON.toJSONString(value) + ", msg="
					+ ex.getMessage();
            log.error(detail, ex);
			throw ex;
		}
	}
	/**
	 * 设置一个对象，可以添加有效期
	 * @param key
	 * @param obj
	 */
	public void setexObj(String key, int expire, Object value) {
		try {
			redisClient.getJedis().setex(key, expire, JSON.toJSONString(value));
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", value=" + JSON.toJSONString(value) + ", msg="
					+ ex.getMessage();
            log.error(detail, ex);
			throw ex;
		}
	}
	/**
	 * 获取一个key的对象
	 * @param key
	 * @param obj
	 */
	public Object getObj(String key, Class<?> c) {
		try {
			String value = redisClient.getJedis().get(key);
			if(StringUtils.isNotEmpty(value)){
					return JSON.parseObject(value, c);
			}
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key  + ", msg="
					+ ex.getMessage();
            log.error(detail, ex);
			throw ex;
		}
		return null;
	}
	/**
	 * 设置一个key的value值
	 * @param key:string
	 * @param value:string
	 */
	public void set(String key,String value){
		try {
			redisClient.getJedis().set(key, value);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", value=" + value + ", msg="
					+ ex.getMessage();
            log.error(detail, ex);
			throw ex;
		}
	}
	/**
	 * 获取一个key的value值
	 * @param key :String
	 * @return :string
	 */
	public String get(String key){
		String result = null;
		try {
			result = redisClient.getJedis().get(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key  + ", msg="
					+ ex.getMessage();
            log.error(detail, ex);
			throw ex;
		}
		return result;
	}
	/**
	 * 设置一个key的value值(List)
	 * @param key:string
	 * @param value:List<Object>
	 */
	public void set(String key,List<?> list){
		try {
			redisClient.getJedis().set(key, JSON.toJSONString(list));
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", value=" + JSON.toJSONString(list) + ", msg="
					+ ex.getMessage();
            log.error(detail, ex);
			throw ex;
		}
	}
	/**
	 * 获取一个key的value值(List)
	 * @param key:string
	 * @param c : List对象类型
	 * @return: List<Object>
	 */
	public List<?> get(String key,Class<?> c){
		String value="";
		try {
			value = redisClient.getJedis().get(key);
			if(StringUtils.isNotEmpty(value)){
					return JSON.parseArray(value, c);
			}
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key  +",value="+value+", msg="
					+ ex.getMessage();
            log.error(detail, ex);
			throw ex;
		}
		return null;
	}
	/**
	 * 设置一个key的value值(list对象+有效期)
	 * @param key:string
	 * @param value:List<Object>
	 * @param ttl:有效期
	 */
	public void setex(String key,int ttl,List<?> list){
		try {
			redisClient.getJedis().setex(key, ttl, JSON.toJSONString(list));
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", value=" + JSON.toJSONString(list) + ", ttl=" + ttl+ ", msg="
					+ ex.getMessage();
            log.error(detail, ex);
			throw ex;
		}
	}
	public void setex(String key, int ttl, String value) {
		try {
			redisClient.getJedis().setex(key, ttl, value);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", value=" + value + ", ttl=" + ttl
					+ ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
	}


	public Long lpush(String key, String... values) {
		Long result = 0L;
		try {
			result = redisClient.getJedis().lpush(key, values);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", values="
					+ Arrays.toString(values) + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public String rpop(String key) {
		String str = null;
		try {
			str = redisClient.getJedis().rpop(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return str;
	}

	public String lpop(String key) {
		String str = null;
		try {
			str = redisClient.getJedis().lpop(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return str;
	}

	public Long hincrBy(String key, String field, long value) {
		Long result = 0L;
		try {
			result = redisClient.getJedis().hincrBy(key, field, value);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", field=" + field + ", value="
					+ value + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public List<String> hmget(String key, String... fields) {
		List<String> list = null;
		try {
			list = redisClient.getJedis().hmget(key, fields);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", fields="
					+ Arrays.toString(fields) + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return list;
	}

	public Boolean sismember(String key, String member) {
		Boolean result = null;
		try {
			result = redisClient.getJedis().sismember(key, member);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", member=" + member + ", msg="
					+ ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Long sadd(String key, String... values) {
		Long result = 0L;
		try {
			result = redisClient.getJedis().sadd(key, values);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", values="
					+ Arrays.toString(values) + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Long srem(String key, String... values) {
		Long result = 0L;
		try {
			result = redisClient.getJedis().srem(key, values);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", values="
					+ Arrays.toString(values) + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Set<String> smembers(String key) {
		Set<String> set = null;
		try {
			set = redisClient.getJedis().smembers(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return set;
	}

	public Boolean expire(String key, int ttl) {
		Boolean result = false;
		try {
			result = redisClient.getJedis().expire(key, ttl) == 1;
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", ttl=" + ttl + ", msg="
					+ ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public void ltrim(String key, long start, long end) {
		try {
			redisClient.getJedis().ltrim(key, start, end);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", start=" + start + ", end=" + end
					+ ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
	}

	public List<String> lrange(String key, long start, long end) {
		List<String> list = null;
		try {
			list = redisClient.getJedis().lrange(key, start, end);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", start=" + start + ", end=" + end
					+ ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return list;
	}

	public Long del(String key) {
		Long result = 0L;
		try {
			result = redisClient.getJedis().del(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg="
					+ ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Long scard(String key) {
		Long result = 0L;
		try {
			result = redisClient.getJedis().scard(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public String spop(String key) {
		String str = null;
		try {
			str = redisClient.getJedis().spop(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return str;
	}

	public Long incr(String key) {
		Long result = 0L;
		try {
			result = redisClient.getJedis().incr(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}
	
	public Long incrBy(String key,Long increment) {
		Long result = 0L;
		try {
			result = redisClient.getJedis().incrBy(key,increment);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	
	
    public Boolean setnx(String key, String value) {
    	Boolean result = false;
        try {
            result = redisClient.getJedis().setnx(key,value) == 1;
        } catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
            log.error(detail, ex);
            throw ex;
        }
        return result;
    }

    public String hget(String key, String field) {
		String str = null;
		try {
			str = redisClient.getJedis().hget(key, field);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", field=" + field + ", msg="
					+ ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return str;
	}

	public Boolean hset(String key, String field, String value) {
		Boolean result = false;
		try {
			result = redisClient.getJedis().hset(key, field, value) == 1;
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", field=" + field + ", value="
					+ value + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Boolean hexists(String key, String field) {
		Boolean result = null;
		try {
			result = redisClient.getJedis().hexists(key, field);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", field=" + field + ", msg="
					+ ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public String lindex(String key, long index) {
		String str = null;
		try {
			str = redisClient.getJedis().lindex(key, index);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", index=" + index + ", msg="
					+ ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return str;
	}

	public Long lrem(String key, long count, String value) {
		Long result = 0L;
		try {
			result = redisClient.getJedis().lrem(key, count, value);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", value=" + value + ", msg="
					+ ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Long llen(String key) {
		Long result = 0L;
		try {
			result = redisClient.getJedis().llen(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}


	public Boolean exists(String key) {
		Boolean result;
		try {
			result = redisClient.getJedis().exists(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public void hmset(String key, Map<String, String> hash) {
		try {
			redisClient.getJedis().hmset(key, hash);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", hash=" + hash + ", msg="
					+ ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
	}

	public Map<String, String> hgetAll(String key) {
		Map<String, String> result;
		try {
			result = redisClient.getJedis().hgetAll(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Long hdel(String key, String... fields) {
		Long result;
		try {
			result = redisClient.getJedis().hdel(key, fields);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", fields="
					+ Arrays.toString(fields) + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Long rpush(String key, String... values) {
		Long result;
		try {
			result = redisClient.getJedis().rpush(key, values);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", values="
					+ Arrays.toString(values) + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Set<String> zrange(String key, long start, long end) {
		Set<String> result;
		try {
			result = redisClient.getJedis().zrange(key, start, end);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", start=" + start + ", end=" + end
					+ ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Set<String> zrange(String key, String start, String end) {
		return zrange(key, Long.parseLong(start), Long.parseLong(end));
	}

	public Boolean zadd(String key, double score, String member) {
		Boolean result =false;
		try {
			result = redisClient.getJedis().zadd(key, score, member) == 1;
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", member=" + member + ", msg="
					+ ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Boolean zadd(String key, String score, String member) {
		return zadd(key, Double.parseDouble(score), member);
	}

	public Long zcard(String key) {
		Long result;
		try {
			result = redisClient.getJedis().zcard(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Double zscore(String key, String member) {
		Double result;
		try {
			result = redisClient.getJedis().zscore(key, member);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", member=" + member + ", msg="
					+ ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Long zrem(String key, String... members) {
		Long result;
		try {
			result = redisClient.getJedis().zrem(key, members);
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", members="
					+ Arrays.toString(members) + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

	public Boolean hsetnx(String key, String field, String value) {
		Boolean result = false;
		try {
			result = redisClient.getJedis().hsetnx(key, field, value) == 1;
		} catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ", field=" + field + ", value="
					+ value + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}

    public Long hlen(String key) {
        Long result;
        try {
            result = redisClient.getJedis().hlen(key);
        } catch (RuntimeException ex) {
            HandleException(ex);
            String detail = "key=" + key + ",msg=" + ex.getMessage();
            log.error(detail, ex);
            throw ex;
        }
        return result;
    }
	
	public Long ttl(String key) {
		Long result = null;
		try {
			result = redisClient.getJedis().ttl(key);
		} catch (RuntimeException ex) {
            HandleException(ex);
			String detail = "key=" + key + ", msg=" + ex.getMessage();
			log.error(detail, ex);
			throw ex;
		}
		return result;
	}
	private void HandleException(RuntimeException ex){
	}
}

