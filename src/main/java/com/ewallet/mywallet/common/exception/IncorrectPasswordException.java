package com.ewallet.mywallet.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The password you entered is incorrect. Try logging in with the correct password.")
public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
    }
}
