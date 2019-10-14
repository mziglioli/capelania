package com.capelania.config.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        logException(ex, request);
        return super.handleExceptionInternal(ex, buildErrorResponse(request, status, ex), headers, status, webRequest);
    }

    @ExceptionHandler
    public ResponseEntity handleGlobalException(HttpServletRequest request, Exception ex) {
        return handleResponseException(request, ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public void handleAuthenticationException(
        HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) {
        handleException(request, response, ex, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public void handleUsernameNotFoundException(
        HttpServletRequest request, HttpServletResponse response, UsernameNotFoundException ex) {
        handleException(request, response, ex, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDenied(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        handleException(request, response, ex, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(SQLGrammarException.class)
    public void handleAccessDenied(HttpServletRequest request, HttpServletResponse response, SQLGrammarException ex) {
        handleException(request, response, ex, HttpStatus.FORBIDDEN);
    }

    private ResponseEntity handleResponseException(HttpServletRequest request, Exception ex, HttpStatus status) {
        logException(ex, request);
        ErrorResponse errorResponse = buildErrorResponse(request, status,ex);
        return buildResponseEntity(errorResponse, status);
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception ex, HttpStatus status) {
        logException(ex, request);
        ErrorResponse errorResponse = buildErrorResponse(request, status, ex);
        response.setStatus(status.value());
        addResponse(request, response, errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        List<Error> errors = bindErrors(ex);
        logException(ex, request);
        logErrors(errors);
        ErrorResponse errorResponse = buildErrorResponse(request, HttpStatus.BAD_REQUEST , "BindResult Exception", errors);
        return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private List<Error> bindErrors(BindException ex){
        List<Error> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<ObjectError> globalErrors = ex.getGlobalErrors();
        if(!CollectionUtils.isEmpty(globalErrors)){
            errors.addAll(globalErrors.stream()
                .map(fe -> new Error(fe.getObjectName(), fe.getDefaultMessage()))
                .collect(Collectors.toList()));
        }
        if(!CollectionUtils.isEmpty(fieldErrors)){
            errors.addAll(fieldErrors.stream().sorted(Comparator.comparing(FieldError::getField))
                .map(fe -> new Error(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList()));
        }
        if(CollectionUtils.isEmpty(errors)){
            errors.add(new Error("exception", "Message not available"));
        }
        return errors;
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse, HttpStatus httpStatus) {
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private ErrorResponse buildErrorResponse(HttpServletRequest request, HttpStatus status, Exception ex) {
        return buildErrorResponse(request, status, ex.getMessage(), Arrays.asList(Error.builder().key("exception").value(status.getReasonPhrase()).build()));
    }

    /**
     * Build a error response
     * This will be used to attached into the {@link ResponseEntity} as a {@link ResponseEntity#body}
     * @param request
     * @param status
     * @param message
     * @param errors
     * @return new {@link ErrorResponse}
     * */
    private ErrorResponse buildErrorResponse(HttpServletRequest request, HttpStatus status, String message, List<Error> errors) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(formatter);

        return ErrorResponse.builder()
            .dateTime(now)
            .timestamp(formatDateTime)
            .status(status.value())
            .errors(errors)
            .path(request.getRequestURI())
            .message(message).build();
    }

    private void logErrors(List<Error> errors) {
        StringBuilder sb = new StringBuilder();
        sb.append("LOG:");
        if (CollectionUtils.isEmpty(errors)) {
            sb.append("empty errors");
        } else {
            errors.forEach(e -> {
                sb.append(e.getKey());
                sb.append(":");
                sb.append(e.getValue());
                sb.append(" | ");
            });
        }
//        log.error(sb.toString());
    }

    private void logException(Exception ex, HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        sb.append("LOG:");
        sb.append(String.format("method:", request.getMethod()));
        sb.append(String.format("uri:", request.getRequestURI()));
        try{
            if(request.getQueryString() != null){
                sb.append(String.format("params:", request.getQueryString()));
            }
//            log.error(sb.toString());
        }catch (Exception e){
//            log.error(sb.toString(), ex);
        }
    }

    /**
     * Add json object into {@link HttpServletResponse}
     *
     * @param request
     * @param servletResponse the http response
     * @param errorResponse the error to be add as json
 *
 *  */
    private void addResponse(HttpServletRequest request,
        HttpServletResponse servletResponse, ErrorResponse errorResponse) {
        servletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            servletResponse.getOutputStream().println(mapper.writeValueAsString(errorResponse));
        } catch (IOException e) {
            logException(e, request);
        }
    }
}