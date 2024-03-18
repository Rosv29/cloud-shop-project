package com.cloud.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cloud.shop.domain.dao.MemberEntityRepository;
import com.cloud.shop.domain.entity.MemberEntity;
import com.cloud.shop.redis.RedisUtil;
import com.cloud.shop.security.MemberRole;

@SpringBootTest
class CloudShopProjectApplicationTests {

	@Autowired
	private MemberEntityRepository repo;
	@Autowired
	private PasswordEncoder encoder;
	//@Test
	void 어드민등록() {
		repo.save(MemberEntity.builder()
				.id("admin")
				.password(encoder.encode("1234"))
				.build()
				.addRole(MemberRole.ADMIN).addRole(MemberRole.USER)
				);
	}
	
	@Autowired
	StringRedisTemplate redisTemplate;
	@Autowired
	RedisUtil redis;
	
	String key="test@test.com";
	
	@Test
	void 저장() {
		redis.setDataExpire(key, "1234", 180);
	}
	//@Test
	void 조회() {
		System.out.println(redis.existData(key)); 
	}
	//@Test
	void 읽어오기() {
		System.out.println(redis.getData(key));
	}

}
