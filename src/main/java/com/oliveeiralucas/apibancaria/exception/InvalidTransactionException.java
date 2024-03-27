package com.oliveeiralucas.apibancaria.exception;

// Classe de exceção para transações inválidas, estendendo RuntimeException
public class InvalidTransactionException extends RuntimeException{

    //Recebe mensagem de erro como parameter
    public InvalidTransactionException (String message){
        super(message);
    }
}
