package com.enterpriseapplications.authenticationspring.dao;

import com.enterpriseapplications.authenticationspring.entities.ExternalUser;
import com.enterpriseapplications.authenticationspring.entities.enums.ExternalProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalUserDao extends JpaRepository<ExternalUser,Long>
{
    @Query("select u from ExternalUser u where u.externalID = :requiredID")
    Optional<ExternalUser> findByExternalID(@Param("requiredID") String requiredID);
    @Query("select u from ExternalUser u where u.externalID = :requiredID and u.externalProvider = :requiredExternalID")
    Optional<ExternalUser> find(@Param("requiredID") Long id,@Param("requiredExternalID") String externalID);

    @Query("select u from ExternalUser u where u.externalProvider = :requiredProvider")
    Page<ExternalUser> findAll(@Param("requiredProvider")ExternalProvider externalProvider, Pageable pageable);
}
