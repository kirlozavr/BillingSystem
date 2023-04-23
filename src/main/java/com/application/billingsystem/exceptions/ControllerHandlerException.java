package com.application.billingsystem.exceptions;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ControllerHandlerException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ApiResponse(responseCode = "404", useReturnTypeSchema = true, description = "Объект не найден")
    public String notFound(NotFoundException exception){
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistsException.class)
    @ApiResponse(responseCode = "409", useReturnTypeSchema = true, description = "Объект уже существует")
    public String alreadyExists(AlreadyExistsException exception){
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectArgumentException.class)
    @ApiResponse(responseCode = "400", useReturnTypeSchema = true, description = "Некорректно введены данные")
    public String incorrectArgument(IncorrectArgumentException exception){
        return exception.getMessage();
    }
}
