package com.synchrony.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synchrony.assignment.model.User;

/**
 * This is User Repository to perform CRUD operation on User details.
 * @author Aviprash
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	  
}
