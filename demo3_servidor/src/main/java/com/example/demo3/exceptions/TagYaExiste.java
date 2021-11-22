package com.example.demo3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TagYaExiste extends Exception{

    public TagYaExiste(){}

    public TagYaExiste(String message){super(message);}
}
