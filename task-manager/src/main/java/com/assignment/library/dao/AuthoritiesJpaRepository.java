package com.assignment.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.library.entities.Authorities;
import com.assignment.library.entities.AuthoritiesId;

@Repository
public interface AuthoritiesJpaRepository extends JpaRepository<Authorities, AuthoritiesId> {

}
