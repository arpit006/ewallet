package com.ewallet.mywallet.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Your Wallet Balance is low. Please add balance to your wallet to enjoy hassle free services.")
public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
    }
}
