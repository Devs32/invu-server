package kr.co.devs32;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static kr.co.devs32.TestController.DEFAULT_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import kr.co.devs32.applcation.InvuApplication;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = InvuApplication.class
)
class TestControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testBootAndHello() {
        // given
        String url = "http://localhost:" + port + "/hello";

        // when
        ResponseEntity<String> response = restTemplate
            .getForEntity(url, String.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains(DEFAULT_MESSAGE);
    }
}
