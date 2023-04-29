package com.sample.springapplicationpositionsearch.repository;


import com.sample.springapplicationpositionsearch.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findById(UUID apiKey);

    Optional<Client> findByEmail(String email);
}

