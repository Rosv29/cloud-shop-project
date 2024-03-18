package com.cloud.shop.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	
	//RedisConnectionFactory는 Redis 서버에 연결을 설정하고 관리하는 역할을 담당
	@Bean
	RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(host,port);
	}

	//JedisConnectionFactory : Jedis는 단일 쓰레드에서만 동작하므로 멀티 스레드 환경에서는 별도의 인스턴스를 사용해야함
	//LettuceConnectionFactory : 비동기 및 반응형 프로그래밍 모델을 지원하며 성능과 확장성이 뛰어납니다.

	@Bean
	RedisTemplate<?, ?> redisTemplate(){
		RedisTemplate<byte[], byte[]> redisTemplate=new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
		
	}
	
}
