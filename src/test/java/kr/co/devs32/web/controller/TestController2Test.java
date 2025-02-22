package kr.co.devs32.web.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(TestController.class)
class TestController2Test {

	@InjectMocks
	private TestController2 testController;

	@Test
	void testHello() {
		String result = testController.hello();
		assertThat(result).contains("test");
	}

}
