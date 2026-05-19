package gr.cf9.MedicalTest.core.exceptions;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends AppGenericException{
    public EntityAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
