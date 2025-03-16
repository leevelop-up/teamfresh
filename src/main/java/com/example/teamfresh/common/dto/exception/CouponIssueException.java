package com.example.teamfresh.common.dto.exception;
import com.example.teamfresh.common.enums.ReturnCode;
import lombok.Getter;

@Getter
public class CouponIssueException extends RuntimeException{
    private final ReturnCode errorCode;
    private final String message;

    public CouponIssueException(ReturnCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "[%s] %s".formatted(errorCode, message);
    }
}
