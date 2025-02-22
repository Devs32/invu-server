package kr.co.devs32.web.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2 {

	public static final String DEFAULT_MESSAGE = "hello INVU";

	@GetMapping("/hello2")
	public String hello() {
		return getHello();
	}

	private String getHello() {
		return "test" + " " + LocalDateTime.now();
	}
}
