package com.projet.korector.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ResourceAlreadyExistsException  extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceAlreadyExistsException(String message)  {
        super(message);
    }

    public String getCode() { return "RESOURCE_ALREADY_EXISTS"; }
}
