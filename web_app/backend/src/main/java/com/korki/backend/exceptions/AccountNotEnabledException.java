package com.korki.backend.exceptions;

public class AccountNotEnabledException extends Exception{

    public AccountNotEnabledException() {
        super("Account is not activated");
    }
}
