package br.com.jherrerocavadas.msauthentication.exception.throwable;

import br.com.jherrerocavadas.msauthentication.util.MessageUtil;
import org.springframework.context.MessageSource;

public class BaseException extends RuntimeException{

    private MessageSource messageSource;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(MessageSource messageSource,
                         String placeholder,
                         Object ...parametros) {
        super(MessageUtil.messageWithParameters(
                messageSource,
                placeholder,
                parametros)
        );
    }
    public BaseException(MessageSource messageSource,
                         String placeholder) {
        super(MessageUtil.messageWithoutParameters(
                messageSource,
                placeholder)
        );
    }
}
