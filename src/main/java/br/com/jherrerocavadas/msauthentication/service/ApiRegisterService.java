package br.com.jherrerocavadas.msauthentication.service;

import br.com.jherrerocavadas.msauthentication.entity.ApiRegister;
import br.com.jherrerocavadas.msauthentication.exception.throwable.ApiNotRegisteredException;
import br.com.jherrerocavadas.msauthentication.exception.throwable.InvalidRequestFieldsException;
import br.com.jherrerocavadas.msauthentication.repository.ApiRegisterRepository;
import br.com.jherrerocavadas.msauthentication.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ApiRegisterService {

    private final ApiRegisterRepository apiRegisterRepository;

    private final MessageSource messageSource;

    public ApiRegisterService(ApiRegisterRepository apiRegisterRepository, MessageSource messageSource) {
        this.apiRegisterRepository = apiRegisterRepository;
        this.messageSource = messageSource;
    }

//    public void save(Object requestDTO){
//        apiRegisterRepository.save(ApiRegister.builder()
//                        .apiKey(requestDTO.getApiKey())
//                        .apiName(requestDTO.getApiName())
//                        .apiSecret(requestDTO.getApiSecret())
//                .build());
//    }

    public ApiRegister findApiByKey(String key){
        try {
            return apiRegisterRepository.findById(UUID.fromString(key)).orElseThrow(() -> {
                throw new ApiNotRegisteredException(messageSource);
            });
        }
        catch(Exception e){
            log.error("Erro ao buscar registro de API: {}", e.getLocalizedMessage());
            if(e instanceof ApiNotRegisteredException){
                throw e;
            }
            throw new InvalidRequestFieldsException(MessageUtil.messageWithParameters(
                    messageSource,
                    "bad.request.exception",
                    e.getLocalizedMessage()
            ));
        }
    }
}
