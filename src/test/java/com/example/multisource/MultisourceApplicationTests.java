package com.example.multisource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultisourceApplicationTests {

	@Resource
	private RedisTemplate redisTemplate;
	@Test
	public void setValue()
	{
		redisTemplate.opsForValue().set("girlFiend","chenxi");
	}

	@Test
	public void getValue()
	{
		String name=(String)redisTemplate.opsForValue().get("girlFiend");
		System.out.println(name);
	}

}
