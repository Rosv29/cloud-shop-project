package com.cloud.shop.redis;

import java.time.Duration;
import java.util.Random;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RedisUtil {

	private final StringRedisTemplate template;
	
	//저장 & 수정
	public void setDataExpire(String key, String value, long seconds) {
		ValueOperations<String, String> ops=template.opsForValue();
		Duration timeout=Duration.ofSeconds(seconds);
		ops.set(key, value, timeout);
	}
	//존재여부확인
	public boolean existData(String key) {
		return template.hasKey(key);
	}
	//value확인
	public String getData(String key) {
		ValueOperations<String, String> ops=template.opsForValue();
		return ops.get(key);
	}
	//삭제
	public void deleteData(String key) {
		template.delete(key);
	}
	
}

