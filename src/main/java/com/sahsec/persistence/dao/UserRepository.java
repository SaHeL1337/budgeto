package com.sahsec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahsec.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);

}