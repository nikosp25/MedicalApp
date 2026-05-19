package gr.cf9.MedicalTest.core.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends AppGenericException {
    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
