package br.com.jherrerocavadas.msauthentication.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Table(name = "api_register_auth")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "api_key", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID apiKey;

    @Column(name = "api_name")
    private String apiName;

    @Column(name = "api_secret")
    private String apiSecret;

}
