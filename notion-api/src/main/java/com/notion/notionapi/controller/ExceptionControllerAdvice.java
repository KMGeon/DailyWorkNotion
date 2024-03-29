package com.notion.notionapi.controller;

import com.notion.notioncore.exception.ErrorResponse;
import com.notion.notioncore.exception.MemberAbstractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .message(e.getMessage())
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        logger.error("handleMethodArgumentNotValidException : {} , StatusCode : {}", e, response.getCode());

        return response;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberAbstractException.class)
    public ErrorResponse invalidRequestHandler(MemberAbstractException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .message(e.getMessage())
                .build();

        logger.info("asdadasd"+e);

        return response;
    }

//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(NotionApiException.class)
//    public ErrorResponse NotionApiException(NotionApiException e) {
//        ErrorResponse response = ErrorResponse.builder()
//                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
//                .message(e.getMessage())
//                .build();
//
//        logger.warn("Notion api exception" + e);
//
//        return response;
//    }
}