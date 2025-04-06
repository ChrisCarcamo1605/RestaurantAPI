package com.unicaes.poo.infra.exceptions;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequest(MethodArgumentNotValidException ex){

        var errors = ex.getFieldErrors().stream().map(DatosErrores::new);
        return  ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(QueryException.class)
    public ResponseEntity queryValidation(QueryException ex){

        var errorResponse = new DatosErrores(
                HttpStatus.BAD_REQUEST.value(), "ERROR AL CREAR CONSULTA",
                ex.getMessage());

return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    public record DatosErrores(
            Integer codigo,
            String campo,
            String error
    ){
        public DatosErrores(FieldError error){
            this(null, error.getField(), error.getDefaultMessage());
        }
    }


}
