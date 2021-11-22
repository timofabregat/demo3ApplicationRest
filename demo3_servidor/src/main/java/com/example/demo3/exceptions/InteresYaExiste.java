package com.example.demo3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class InteresYaExiste extends Exception{

    public InteresYaExiste(String message){super(message);}

    public InteresYaExiste(){}


}
