package com.eazybytes.jobportal.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.eazybytes.jobportal.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex,WebRequest request){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
            request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR,
            ex.getMessage(),LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDTO> handleNullPointerException(Exception ex,WebRequest request){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
            request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR,
            "A NullPointerException occured Due to: "+ex.getMessage(),LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> MethodArgumentNotValidException(MethodArgumentNotValidException ex){
       Map<String, String> errors = new HashMap<>();
       List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
       for(FieldError error : fieldError){
         errors.put(error.getField(),error.getDefaultMessage());
       }
       return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    } 

}
