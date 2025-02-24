package kr.co.devs32.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Example API", description = "Swagger 예제 API")
public class SwaggerApiController {

    @Operation(summary = "Hello API", description = "이 API는 Hello 메시지를 반환합니다.")
    @GetMapping("/test")
    public String test(@RequestParam(name = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }

}
