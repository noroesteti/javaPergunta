package com.example.javaPergunta.infra.handler;


import com.example.javaPergunta.domain.exceptions.NotFoundException;
import com.example.javaPergunta.domain.valueobject.DateClass;
import com.example.javaPergunta.rest.endpoints.resources.ErrorResource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.ZoneId;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private DateClass date;

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity handlerNotFoundException(NotFoundException exception,
                                                                      HttpServletRequest request, WebRequest webRequest) {
        ErrorResource errorResource = problemBuilder(HttpStatus.NOT_FOUND, exception.getMessage(), request.getContextPath()).build();
        LOGGER.info("Error occured, please take me out of here s%",date.getDateTime());
        for(String s : ZoneId.getAvailableZoneIds()){
            System.out.println(s);
        }

        return handleExceptionInternal(exception, errorResource, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }


    private ErrorResource.ErrorResponseBuilder problemBuilder(HttpStatus status, String detail, String path) {
        return new ErrorResource.ErrorResponseBuilder()
                .withTitle(status.getReasonPhrase())
                .withTimesTamp(date.getDateTime())
                .withStatus(status.value())
                .withDetail(detail)
                .withPath(path);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = new ErrorResource.ErrorResponseBuilder()
                    .withTitle(status.getReasonPhrase())
                    .withTimesTamp(date.getDateTime())
                    .withStatus(status.value())
                    .withDetail(exception.getMessage())
                    .withPath(request.getContextPath())
                    .build();
        } else if (body instanceof String) {
            body = new ErrorResource.ErrorResponseBuilder()
                    .withTitle(status.getReasonPhrase())
                    .withTimesTamp(date.getDateTime())
                    .withStatus(status.value())
                    .withDetail(exception.getMessage())
                    .withPath((String) body)
                    .build();
        }

        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

}
