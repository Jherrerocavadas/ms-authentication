package br.com.jherrerocavadas.msauthentication.service;

import br.com.jherrerocavadas.msauthentication.dto.request.TokenGenerationRequestDTO;
import br.com.jherrerocavadas.msauthentication.util.MessageUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    @Value("${application.name:ms-authentication}")
    private String issuer;

    private final MessageSource messageSource;

    @Value("${jwt-expiration-minutes:15}")
    private Long expirationMinutes;

    @Autowired
    public JwtService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String generateJwtToken(TokenGenerationRequestDTO tokenGenerationRequestDTO){
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        return Jwts.builder()
//                .setHeaderParam("alg", "HS256")
//                .setHeaderParam("typ", "JWT")
                .setIssuer(issuer)
                .setSubject(tokenGenerationRequestDTO.getApiKey())
                .setIssuedAt(this.generateIssuedDate())
                .setExpiration(this.generateExpirationDate())
                .setAudience(tokenGenerationRequestDTO.getApiName())
                .signWith(this.generateKey(tokenGenerationRequestDTO.getApiSecret()), algorithm)
                .compact();

    }

    public boolean checkJwtToken(String token, String apiSecret){
        try {

            var jwtChecker = Jwts.parserBuilder().setSigningKey(this.generateKey(apiSecret)).build();

            jwtChecker.isSigned(token);
            var jwtToken = jwtChecker.parseClaimsJws(token);

            boolean isIssuedByThis = issuer.equals(jwtToken.getBody().getIssuer());
            boolean isNotExpired = jwtToken.getBody().getExpiration().after(Date.from(Instant.now()));

            return jwtChecker.isSigned(token) && isIssuedByThis && isNotExpired;

        } catch (ExpiredJwtException exception){
            throw new JwtException(MessageUtil.messageWithoutParameters(messageSource, "jwt.invalid.or.expired.exception"));
        }
    }

    private Date generateIssuedDate(){
        return Date.from(Instant.now());

    }

    private Date generateExpirationDate(){
        return Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES));

    }

    private Key generateKey(String secretKey){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public Long getDuration() {
        return expirationMinutes;
    }

    public Claims getClaims(String token) {
        int i = token.lastIndexOf('.');
        String withoutSignature = token.substring(0, i+1);
        return Jwts.parserBuilder().build().parseClaimsJwt(withoutSignature).getBody();
    }


}
