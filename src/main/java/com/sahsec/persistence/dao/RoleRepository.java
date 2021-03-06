package com.sahsec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahsec.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}	
