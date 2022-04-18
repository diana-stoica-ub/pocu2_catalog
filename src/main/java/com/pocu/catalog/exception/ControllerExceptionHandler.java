package com.pocu.catalog.exception;

import com.pocu.catalog.web.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR_CODE = "INTERNAL_SERVER_ERROR";
    private static final String ENTITY_NOT_FOUND_CODE = "ENTITY_NOT_FOUND";

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

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(HttpServletRequest request, Exception exception) {
        logger.error("Internal server error", exception);
        return new ErrorDto(INTERNAL_SERVER_ERROR_CODE, "An error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


}
