package com.example.demo3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class NombreDeUsuarioYaExiste extends Exception{

    public NombreDeUsuarioYaExiste(String message){super(message);}

    public NombreDeUsuarioYaExiste(){}


}
