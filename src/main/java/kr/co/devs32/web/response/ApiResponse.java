package kr.co.devs32.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;     //HTTP 상태코드
    private String message; //응답메시지
    private T data;         //응답데이터

    //성공 응답(data 포함)
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data);
    }

    //성공 응답(data x 메시지만)
    public static ApiResponse<String> success(String message) {
        return new ApiResponse<>(200, message, null);
    }

    // 실패 응답
    public static ApiResponse<String> error(int status, String message) {
        return new ApiResponse<>(status, message, null);
    }
}
