/*
 * SPDX-License-Identifier: Apache-2.0
 * SPDX-FileCopyrightText: Huawei Inc.
 *
 */

package org.eclipse.xpanse.api.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.xpanse.modules.models.common.exceptions.ResponseInvalidException;
import org.eclipse.xpanse.modules.models.common.exceptions.XpanseUnhandledException;
import org.eclipse.xpanse.modules.models.response.Response;
import org.eclipse.xpanse.modules.models.response.ResultType;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Exception handler for the REST API.
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * Exception handler for MethodArgumentNotValidException.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException: ", ex);
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        return Response.errorResponse(ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    /**
     * Exception handler for RuntimeException.
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleRuntimeException(RuntimeException ex) {
        String failMessage = ex.getMessage();
        log.error("handleRuntimeException: ", ex);
        return Response.errorResponse(ResultType.RUNTIME_ERROR,
                Collections.singletonList(failMessage));
    }

    /**
     * Exception handler for HttpMessageConversionException.
     */
    @ExceptionHandler({HttpMessageConversionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleHttpMessageConversionException(HttpMessageConversionException ex) {
        log.error("handleHttpMessageConversionException: ", ex);
        String failMessage = ex.getMessage();
        return Response.errorResponse(ResultType.BAD_PARAMETERS,
                Collections.singletonList(failMessage));
    }

    /**
     * Exception handler for IllegalArgumentException.
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("handleIllegalArgumentException: ", ex);
        String failMessage = ex.getMessage();
        return Response.errorResponse(ResultType.BAD_PARAMETERS,
                Collections.singletonList(failMessage));
    }

    /**
     * Exception handler for Exception.
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleException(Exception ex) {
        log.error("handleException: ", ex);
        String failMessage = ex.getClass().getName() + ":" + ex.getMessage();
        return Response.errorResponse(ResultType.RUNTIME_ERROR,
                Collections.singletonList(failMessage));
    }

    /**
     * Exception handler for ResponseInvalidException.
     */
    @ExceptionHandler({ResponseInvalidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public Response handleResponseInvalidException(
            ResponseInvalidException ex) {
        return Response.errorResponse(ResultType.INVALID_RESPONSE, ex.getErrorReasons());
    }

    /**
     * Exception handler for ResponseInvalidException.
     */
    @ExceptionHandler({XpanseUnhandledException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response handleXpanseUnhandledException(
            XpanseUnhandledException ex) {
        return Response.errorResponse(ResultType.UNHANDLED_EXCEPTION,
                Collections.singletonList(ex.getMessage()));
    }

}