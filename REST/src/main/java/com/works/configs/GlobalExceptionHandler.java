package com.works.configs;

import com.works.utils.ERest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity exceptionHandler(MethodArgumentNotValidException ex ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, false);
        hm.put(ERest.errors, errorParse(ex));
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    public List<Map> errorParse( MethodArgumentNotValidException ex ) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<Map> mapList = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            Map<String, String > hm = new HashMap<>();
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            hm.put("field", fieldName);
            hm.put("message", message);
            mapList.add(hm);
        }
        return mapList;
    }

}
