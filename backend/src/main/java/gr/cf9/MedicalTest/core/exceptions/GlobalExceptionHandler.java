package gr.cf9.MedicalTest.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        // Loops through the DTO fields and maps the errors.
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Returns a 400 Bad Request to Postman with my custom messages
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

   @ExceptionHandler(AppGenericException.class)
    public ResponseEntity<String> handleAppGenericException (AppGenericException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                ex.getStatus()
        );
   }
}

