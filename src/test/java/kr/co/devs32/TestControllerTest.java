package kr.co.devs32;

import static kr.co.devs32.web.controller.TestController.DEFAULT_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.devs32.web.controller.TestController;

@WebMvcTest(TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TestController testController;

    @Test
    void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/hello"))
            .andExpect(status().isOk());
    }

    @Test
    void testHello() {
        String result = testController.hello();
        assertThat(result).contains(DEFAULT_MESSAGE);
    }
}
