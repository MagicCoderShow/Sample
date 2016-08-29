package com.dream404.sample.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * redis-sample
 * 
 * @author dream404
 *
 */
public class RedisSample {
	Jedis jedis = RedisUtil.getJedis();

	public static void main(String[] args) {
		RedisSample rs = new RedisSample();
		// 测试String
		// rs.operateString();
		// 测试map
		// rs.operateMap();
		// 测试list
		// rs.operateList();
		// 测试set
		rs.operateSet();
	}

	/**
	 * redis存储字符串
	 */
	public void operateString() {
		System.out.println("------------redis存储字符串 start----------------");

		// -----添加数据----------
		jedis.set("name", "dream404");// 向key-->name中放入了value-->deram404
		System.out.println(jedis.get("name"));// 执行结果：dream404

		jedis.append("name", " is my lover"); // 拼接
		System.out.println(jedis.get("name"));

		jedis.del("name"); // 删除某个键
		System.out.println(jedis.get("name"));

		// 设置多个键值对
		jedis.mset("name", "dream404", "age", "24", "address", "北京");

		// 进行加1操作
		jedis.incr("age");

		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("address"));

		System.out.println("------------redis存储字符串 end----------------");
	}

	/**
	 * redis存储map
	 */
	public void operateMap() {
		System.out.println("------------redis存储map start----------------");

		// 创建map
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "dream404");
		map.put("age", "24");
		map.put("address", "北京");
		// 存储
		jedis.hmset("map", map);

		// 获取
		List<String> rsmap = jedis.hmget("map", "name", "age", "address");
		System.out.println(rsmap);

		// 删除map中的某个键值
		jedis.hdel("map", "age");
		// 获取user中的age
		System.out.println("map中的age" + jedis.hmget("map", "age"));
		// 获取user的长度
		System.out.println("map长度" + jedis.hlen("map"));
		System.out.println("map中的所有key" + jedis.hkeys("map"));
		System.out.println("map中的所有value" + jedis.hvals("map"));

		// 遍历key
		Iterator<String> iter = jedis.hkeys("map").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("map", key));
		}

		System.out.println("------------redis存储map end----------------");
	}

	/**
	 * jedis操作List
	 */
	public void operateList() {
		// 开始前，先移除所有的内容
		jedis.del("list");

		System.out.println(jedis.lrange("list", 0, -1));

		// 先向1 2 3中存放三条数据
		jedis.lpush("list", "1");
		jedis.lpush("list", "2");
		jedis.lpush("list", "3");

		// 再取出所有数据jedis.lrange是按范围取出，
		// 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
		System.out.println(jedis.lrange("list", 0, -1));

		jedis.del("list");
		jedis.rpush("list", "1");
		jedis.rpush("list", "2");
		jedis.rpush("list", "3");
		System.out.println(jedis.lrange("list", 0, -1));
	}

	/**
	 * jedis操作Set
	 */
	public void operateSet() {
		// 添加
		jedis.sadd("set", "1");
		jedis.sadd("set", "2");
		jedis.sadd("set", "3");
		jedis.sadd("set", "4");
		jedis.sadd("set", "5");
		
		// 获取所有加入的value
		System.out.println(jedis.smembers("set"));
		// 移除
		jedis.srem("set", "1");
		// 获取所有加入的value
		System.out.println(jedis.smembers("set"));
		// 判断是否是集合元素
		System.out.println(jedis.sismember("set", "5"));
		// 返回一个随机元素
		System.out.println(jedis.srandmember("set"));
		// 返回集合的元素个数
		System.out.println(jedis.scard("set"));
	}
}
