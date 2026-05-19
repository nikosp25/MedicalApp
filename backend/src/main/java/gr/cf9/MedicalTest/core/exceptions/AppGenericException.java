package gr.cf9.MedicalTest.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppGenericException extends RuntimeException {
    private final HttpStatus status;

    public AppGenericException(String message,HttpStatus status) {
        super(message);
        this.status = status;
    }


}
