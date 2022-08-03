package com.kata.kidek.rest.repository;

import com.kata.kidek.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
    User getUserByEmail(String mail);
    User getUserById(Long id);
}

