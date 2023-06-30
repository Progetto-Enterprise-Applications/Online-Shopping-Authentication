package com.enterpriseapplications.authenticationspring.dao;

import com.enterpriseapplications.authenticationspring.entities.User;
import com.enterpriseapplications.authenticationspring.entities.enums.ExternalProvider;
import com.enterpriseapplications.authenticationspring.entities.enums.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long>
{
    @Query("select u from User u where u.email = :requiredEmail")
    Optional<User> findByEmail(@Param("requiredEmail") String email);

    @Query("select u from User u where u.userType = :requiredType")
    Page<User> findByType(@Param("requiredType") UserType userType,Pageable pageable);

    @Query("select u from User u where u.roles LIKE %:requiredRoles%")
    Page<User> findByRoles(@Param("requiredRoles") String roles,Pageable pageable);
}
