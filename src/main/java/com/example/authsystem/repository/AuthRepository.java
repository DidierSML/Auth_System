package com.example.authsystem.repository;

import com.example.authsystem.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Token,Long> {

    /*
    @Query("SELECT t FROM Token t WHERE t.token = :token AND t.usado = false AND t.tipo = :tipo")
    Optional<Token> buscarTokenValido(@Param("token") String token, @Param("tipo") TokenType tipo);

     */
    Optional<Token> findByToken (String token);

}
