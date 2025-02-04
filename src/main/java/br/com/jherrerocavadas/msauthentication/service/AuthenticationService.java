package br.com.jherrerocavadas.msauthentication.service;

import br.com.jherrerocavadas.msauthentication.dto.request.TokenGenerationRequestDTO;
import br.com.jherrerocavadas.msauthentication.dto.response.TokenGenerationResponseDTO;
import br.com.jherrerocavadas.msauthentication.exception.throwable.ApiUnauthorizedException;
import br.com.jherrerocavadas.msauthentication.util.MessageUtil;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {


    private final JwtService jwtService;

    private final ApiRegisterService apiRegisterService;

    private final MessageSource messageSource;

    public AuthenticationService(JwtService jwtService,
                                 ApiRegisterService apiRegisterService,
                                 MessageSource messageSource) {
        this.jwtService = jwtService;
        this.apiRegisterService = apiRegisterService;
        this.messageSource = messageSource;
    }

    public TokenGenerationResponseDTO autenticar(TokenGenerationRequestDTO tokenGenerationRequestDTO) {
        var apiRegister = apiRegisterService.findApiByKey(tokenGenerationRequestDTO.getApiKey());

        boolean apiSecretEquals = apiRegister.getApiSecret().equals(tokenGenerationRequestDTO.getApiSecret());
        boolean apiNameEquals = apiRegister.getApiName().equals(tokenGenerationRequestDTO.getApiName());
        if(apiSecretEquals && apiNameEquals){
            return TokenGenerationResponseDTO.builder()
                    .token(jwtService.generateJwtToken(tokenGenerationRequestDTO))
                    .duracaoMinutos(jwtService.getDuration())
                    .build();
        }
        throw new ApiUnauthorizedException(messageSource, "api.unauthorized.exception");


    }

    public boolean checarAutenticacao(String token) {

        var apiRegister = apiRegisterService.findApiByKey(jwtService.getClaims(token).getSubject());
        if(jwtService.checkJwtToken(token, apiRegister.getApiSecret())){
            return true;
        }
        throw new JwtException(MessageUtil.messageWithoutParameters(messageSource, "jwt.invalid.or.expired.exception"));

    }
}

