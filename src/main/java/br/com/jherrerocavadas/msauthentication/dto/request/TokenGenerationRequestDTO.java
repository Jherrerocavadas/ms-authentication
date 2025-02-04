package br.com.jherrerocavadas.msauthentication.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenGenerationRequestDTO {

    String apiName;
    String apiKey;
    String apiSecret;
}
