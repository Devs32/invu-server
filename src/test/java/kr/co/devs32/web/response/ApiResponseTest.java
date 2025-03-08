package kr.co.devs32.web.response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {
    @Test
    void testSuccessWithData() {
        ApiResponse<Integer> response = ApiResponse.success(1);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getData()).isEqualTo(1);
    }

    @Test
    void testSuccessWithMessage() {
        ApiResponse<String> response = ApiResponse.success("Operation completed");

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Operation completed");
        assertThat(response.getData()).isNull();
    }

    @Test
    void testErrorResponse() {
        ApiResponse<String> response = ApiResponse.error(400, "Bad Request");

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.getMessage()).isEqualTo("Bad Request");
        assertThat(response.getData()).isNull();
    }
}