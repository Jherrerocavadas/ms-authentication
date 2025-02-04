package br.com.jherrerocavadas.msauthentication.exception.throwable;

import br.com.jherrerocavadas.msauthentication.util.Utils;
import org.springframework.context.MessageSource;

public class BaseException extends RuntimeException{

    private MessageSource messageSource;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(MessageSource messageSource,
                         String placeholder,
                         Object ...parametros) {
        super(Utils.messageWithParameters(
                messageSource,
                placeholder,
                parametros)
        );
    }
    public BaseException(MessageSource messageSource,
                         String placeholder) {
        super(Utils.messageWithoutParameters(
                messageSource,
                placeholder)
        );
    }
}
