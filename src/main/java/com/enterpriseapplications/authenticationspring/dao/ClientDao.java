package com.enterpriseapplications.authenticationspring.dao;

import com.enterpriseapplications.authenticationspring.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDao extends JpaRepository<Client,Long> {
    Optional<Client> findByClientID(String clientID);
}
