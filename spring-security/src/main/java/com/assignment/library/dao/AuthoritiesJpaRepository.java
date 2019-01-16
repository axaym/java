package com.assignment.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.library.entities.Authorities;
import com.assignment.library.entities.AuthoritiesId;

public interface AuthoritiesJpaRepository extends JpaRepository<Authorities, AuthoritiesId> {

}
