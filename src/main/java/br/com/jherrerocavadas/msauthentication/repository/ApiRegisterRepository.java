package br.com.jherrerocavadas.msauthentication.repository;

import br.com.jherrerocavadas.msauthentication.entity.ApiRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ApiRegisterRepository extends JpaRepository<ApiRegister, UUID> {
}
