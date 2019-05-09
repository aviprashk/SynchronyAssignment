package com.synchrony.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synchrony.assignment.model.UserImages;

/**
 * This is UserImage Repository to perform CRUD operation on UserImage details.
 * @author ABC
 *
 */
@Repository
public interface UserImagesRepository extends JpaRepository<UserImages, Long>{
}
