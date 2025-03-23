package kr.co.devs32.web.handler;

public class CustomException {
    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
