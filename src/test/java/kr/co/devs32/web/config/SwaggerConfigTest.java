package kr.co.devs32.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = SwaggerConfig.class)
class SwaggerConfigTest {

    @Autowired
    private OpenAPI openAPI;
    @Test
    void testCustomOpenAPI() {
        assertNotNull(openAPI);

        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("API 문서", info.getTitle());
        assertEquals("1.0", info.getVersion());
        assertEquals("Spring Boot + Swagger 연동 예제", info.getDescription());
    }
}