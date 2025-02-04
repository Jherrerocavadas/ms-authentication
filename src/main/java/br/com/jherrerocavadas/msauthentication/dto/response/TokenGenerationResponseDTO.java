package br.com.jherrerocavadas.msauthentication.dto.response;

import lombok.Builder;

@Builder
public record TokenGenerationResponseDTO(String token, Long duracaoMinutos){}
