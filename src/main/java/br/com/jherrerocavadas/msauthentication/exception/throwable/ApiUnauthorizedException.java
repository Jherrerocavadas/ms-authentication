package br.com.jherrerocavadas.msauthentication.exception.throwable;

import org.springframework.context.MessageSource;

public class ApiUnauthorizedException extends BaseException{

    public ApiUnauthorizedException(String message) {
        super(message);
    }

    public ApiUnauthorizedException(MessageSource messageSource, String placeholder) {
        super(messageSource, placeholder);
    }

    public ApiUnauthorizedException(MessageSource messageSource) {
        super(messageSource,"api.unauthorized.exception");
    }
}
