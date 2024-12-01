package no.kristiania.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer not found")
public class CustomerAddressNotFoundException extends RuntimeException {
    public CustomerAddressNotFoundException(String message){
        super(String.valueOf(message));
    }
}
