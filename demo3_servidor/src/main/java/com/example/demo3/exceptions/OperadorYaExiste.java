package com.example.demo3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class OperadorYaExiste extends Exception{

    public OperadorYaExiste(String message){super(message);}

    public OperadorYaExiste(){}

}
