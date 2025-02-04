package br.com.jherrerocavadas.msauthentication.exception.throwable;

import org.springframework.context.MessageSource;

public class InvalidRequestFieldsException extends BaseException{

    public InvalidRequestFieldsException(String message) {
        super(message);
    }


    public InvalidRequestFieldsException(MessageSource messageSource) {
        super(messageSource,"bad.request.exception");
    }
}