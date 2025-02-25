package kr.co.devs32.web.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(SwaggerApiController.class)
class SwaggerApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private SwaggerApiController swaggerApiController;

    @Test
    void testHelloApiWithDefaultName() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void testHelloApiWithCustomName() throws Exception {
        mockMvc.perform(get("/test").param("name", "Jay"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Jay!"));
    }

}