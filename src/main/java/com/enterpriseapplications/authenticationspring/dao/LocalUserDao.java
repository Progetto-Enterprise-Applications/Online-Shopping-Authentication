package com.enterpriseapplications.authenticationspring.dao;

import com.enterpriseapplications.authenticationspring.entities.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalUserDao extends JpaRepository<LocalUser,Long>
{
    @Query("select u from LocalUser u where u.username = :requiredUsername")
    Optional<LocalUser> findUserByUsername(@Param("requiredUsername") String username);
    @Query("select u from LocalUser u where u.id = :requiredID and u.username = :requiredUsername")
    Optional<LocalUser> findUser(@Param("requiredID") Long requiredID,@Param("requiredUsername") String username);
}
