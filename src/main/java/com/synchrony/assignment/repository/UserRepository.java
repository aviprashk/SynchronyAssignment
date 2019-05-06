package com.synchrony.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.synchrony.assignment.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
