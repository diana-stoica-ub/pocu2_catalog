package com.pocu.catalog.exception;

import com.pocu.catalog.web.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR_CODE = "INTERNAL_SERVER_ERROR";
    private static final String ENTITY_NOT_FOUND_CODE = "ENTITY_NOT_FOUND";
    private static final String VALIDATION_FAILED_CODE = "VALIDATION_FAILED";
    private static final String MISSING_REQUEST_PARAM_CODE = "MISSING_REQUEST_PARAM";

    private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler({SubjectNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleSubjectNotFound(HttpServletRequest request, Exception exception) {
        SubjectNotFoundException subjectNotFoundException = (SubjectNotFoundException) exception;
        logger.warn("Not found exception", exception);
        return new ErrorDto(subjectNotFoundException.getErrorCode(), subjectNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleResultDataEmpty(HttpServletRequest request, Exception exception) {
        logger.warn("No entity found in database", exception);
        return new ErrorDto(ENTITY_NOT_FOUND_CODE, "No entity/entities found in database",
                HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler({MethodNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleMethodNotSupported(HttpServletRequest request, Exception exception) {
        logger.warn("Method not supported", exception);
        return new ErrorDto(INTERNAL_SERVER_ERROR_CODE, "Method not supported",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleMissingRequestParam(HttpServletRequest request, Exception exception) {
        logger.warn("Missing request parameter", exception);

        return new ErrorDto(MISSING_REQUEST_PARAM_CODE, exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleArgumentNotValid(HttpServletRequest request, Exception exception) {
        logger.warn("Validation failed for request", exception);
        MethodArgumentNotValidException notValidException = (MethodArgumentNotValidException) exception;
        List<ObjectError> allErrors = notValidException.getBindingResult().getAllErrors();
        StringBuilder message = new StringBuilder();
        for (ObjectError error : allErrors) {
            message.append(error.getDefaultMessage());
            message.append(";");
        }

        return new ErrorDto(VALIDATION_FAILED_CODE, message.toString(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(HttpServletRequest request, Exception exception) {
        logger.error("Internal server error", exception);
        return new ErrorDto(INTERNAL_SERVER_ERROR_CODE, "An error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


}
