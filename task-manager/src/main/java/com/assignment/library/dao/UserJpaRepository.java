package com.assignment.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.library.entities.Users;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<Users, String> {

	
	/*@Query("select u from Users u join fetch u.authoritieses where 1=1")
	public List<Users> getUsersAll();*/
	
	/*{
        "username": "axay",
        "password": "$2a$10$s7gw1i9/FN3rOPS3NwYeOuZ5kfncU3qV6LXEPCWwkyonV.esMmOnK",
        "enabled": true,
        "authoritieses": [
            {
                "id": {
                    "username": "axay",
                    "authority": "ROLE_PRIN"
                }
            }
        ]
    }*/
}
