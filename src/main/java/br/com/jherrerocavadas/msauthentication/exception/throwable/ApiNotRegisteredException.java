package br.com.jherrerocavadas.msauthentication.exception.throwable;

import org.springframework.context.MessageSource;

public class ApiNotRegisteredException extends BaseException{

    public ApiNotRegisteredException(String message) {
        super(message);
    }


    public ApiNotRegisteredException(MessageSource messageSource) {
        super(messageSource,"api.not.registered.exception");
    }
}