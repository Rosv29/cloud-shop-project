package com.cloud.shop.utility;

import org.springframework.mail.javamail.JavaMailSender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MailUtility {

	private final JavaMailSender sender;
	
	
}
