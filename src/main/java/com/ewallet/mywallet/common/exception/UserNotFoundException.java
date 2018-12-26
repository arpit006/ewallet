package com.ewallet.mywallet.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "You don't have any account in the E-wallet, try creating a new Account in a few Simple steps!.")
public class UserNotFoundException extends RuntimeException {
}
