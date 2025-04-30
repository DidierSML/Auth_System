package com.example.authsystem.repository;

import com.example.authsystem.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Token,Long> {

    Optional<Token> findByToken (String token);

}
