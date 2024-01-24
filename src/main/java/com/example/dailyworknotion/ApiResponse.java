package com.example.dailyworknotion;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {


    private HttpStatus code;

    private String message;

    private T data;

    public static <T> ApiResponse<T> success(HttpStatus code, T data){
        return new ApiResponse<>(code, "", data);
    }


//    public static ApiResponse<Object> error(ErrorCode errorCode) {
//        return new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), null);
//    }
//
//    public static ApiResponse<Object> error(ErrorCode errorCode, String message) {
//        return new ApiResponse<>(errorCode.getCode(), message, null);
//    }

}
