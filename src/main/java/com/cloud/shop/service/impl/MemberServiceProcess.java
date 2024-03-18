package com.cloud.shop.service.impl;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloud.shop.domain.dao.MemberEntityRepository;
import com.cloud.shop.domain.dto.MemberSignupDTO;
import com.cloud.shop.redis.RedisUtil;
import com.cloud.shop.security.MemberRole;
import com.cloud.shop.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceProcess implements MemberService {

	private final MemberEntityRepository repo;
	private final PasswordEncoder encoder;
	private final JavaMailSender sender;
	private final String senderEmail = "fjoker1@gmail.com";
	private final StringRedisTemplate redisTemplate;
	private final RedisUtil redis;
	
	
	@Override
	public void saveProcess(MemberSignupDTO dto) {
		repo.save(dto.toEntity(encoder).addRole(MemberRole.USER));

	}

	@Override
	public boolean checkId(String id) {

		if (repo.findById(id).isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean checkEmail(String email) {
		if (repo.findByEmail(email).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void verifyEmail(String email) {
		MimeMessage message = sender.createMimeMessage();
		String code =new Random().ints(48, 58).limit(6)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		try {
			message.setFrom(senderEmail);
			message.setRecipients(MimeMessage.RecipientType.TO, email);
			message.setSubject("이메일 인증");
			String body = "";
			body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
			body += "<h1>" + code + "</h1>";
			body += "<h3>" + "감사합니다." + "</h3>";
			message.setText(body, "UTF-8", "html");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);
		redis.setDataExpire(email, code, 180);
	}

	@Override
	public String verifyCode(String email) {
		
		return redis.getData(email);
	}


}
