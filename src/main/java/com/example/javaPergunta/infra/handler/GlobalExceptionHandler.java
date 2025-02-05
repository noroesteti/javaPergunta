package com.example.javaPergunta.infra.handler;


import com.example.javaPergunta.domain.exceptions.FileNotFoundException;
import com.example.javaPergunta.domain.exceptions.IOException;
import com.example.javaPergunta.domain.exceptions.NotFoundException;
import com.example.javaPergunta.domain.valueobject.DateClass;
import com.example.javaPergunta.rest.endpoints.resources.ErrorResource;
import com.google.api.Http;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private DateClass date;
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity handlerNotFoundException(NotFoundException exception,
                                                                      HttpServletRequest request, WebRequest webRequest) {
        ErrorResource errorResource = problemBuilder(HttpStatus.NOT_FOUND, exception.getMessage(), request.getContextPath()).build();
        LOGGER.info("Error occured - ",date.getDateTime());
        return handleExceptionInternal(exception, errorResource, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(IllegalStateException.class)
    public final ResponseEntity handlerIllegalStateException(IllegalStateException exception,
                                                             HttpServletRequest request,
                                                             WebRequest webRequest){
        ErrorResource errorResource = problemBuilder(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), request.getContextPath()).build();
        LOGGER.info("Error occured - ", date.getDateTime());
        return handleExceptionInternal(exception, errorResource, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ExceptionHandler(IOException.class)
    public final ResponseEntity handlerIOException(IOException exception,
                                                   HttpServletRequest request,
                                                   WebRequest webRequest){
        ErrorResource errorResource = problemBuilder(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), request.getContextPath()).build();
        LOGGER.info("ERROR occured - ", date.getDateTime());
        return handleExceptionInternal(exception, errorResource, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
    @ExceptionHandler(FileNotFoundException.class)
    public final ResponseEntity handlerFileNotFoundException(FileNotFoundException exception,
                                                             HttpServletRequest request,
                                                             WebRequest webRequest){
        ErrorResource errorResource = problemBuilder(HttpStatus.NOT_FOUND, exception.getMessage(), request.getContextPath()).build();
        return handleExceptionInternal(exception, errorResource, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity handlerIllegalArgumentException(IllegalArgumentException exception,
                                                                HttpServletRequest request,
                                                                WebRequest webRequest){
        ErrorResource errorResource = problemBuilder(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), request.getContextPath()).build();
        return handleExceptionInternal(exception, errorResource, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
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
