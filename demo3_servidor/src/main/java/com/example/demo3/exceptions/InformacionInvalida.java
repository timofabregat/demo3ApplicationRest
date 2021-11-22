package com.example.demo3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InformacionInvalida extends Exception{

    public InformacionInvalida(String message){super(message);}

    public InformacionInvalida(){}
}
