package kr.co.devs32;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	public static final String DEFAULT_MESSAGE = "hello INVU";

	@GetMapping("/hello")
	public String hello() {
		return getHello();
	}

	private String getHello() {
		return DEFAULT_MESSAGE + " " + LocalDateTime.now();
	}
}
