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

    // This method ONLY listens for your DTO tags (@NotEmpty, @Pattern, @CustomAgeCheck) failing.
    // It is a built-in Java exception, you do not need to create anything else.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        // This loops through the bad DTO fields and grabs the custom text you typed
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Returns a 400 Bad Request to Postman with your exact messages
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

