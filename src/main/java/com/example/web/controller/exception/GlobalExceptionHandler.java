package com.example.web.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.service.exception.EntityNotFoundException;
import com.example.service.exception.ServiceException;
import com.example.web.model.response.ErrorResponse;



@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleEntitynotFoundException(EntityNotFoundException ex) {
        logger.error(ex.getMessage(), ex);
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()); 
    }
    
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ErrorResponse handleServiceException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse handleException(Exception ex) {
        logger.error(ex.getCause().getMessage(), ex);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getCause().getMessage());
    }

   
}
