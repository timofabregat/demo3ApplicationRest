package com.example.demo3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AdminYaExiste extends Exception{

    public AdminYaExiste(String message){super(message);}

    public AdminYaExiste(){}
}
