package com.example.teamfresh.common.dto.response;

import com.example.teamfresh.common.enums.ReturnCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class ApiResponse<T> {

    private String returnCode;
    private String returnMessage;
    private T data;

    public static <T> ApiResponse of(ReturnCode returnCode, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.returnCode = returnCode.getReturnCode();
        response.returnMessage = returnCode.getReturnMessage();
        response.data = data;
        return response;
    }

    public static <T> ApiResponse of(ReturnCode returnCode) {
        ApiResponse<T> response = new ApiResponse<>();
        response.returnCode = returnCode.getReturnCode();
        response.returnMessage = returnCode.getReturnMessage();

        return response;
    }

}
