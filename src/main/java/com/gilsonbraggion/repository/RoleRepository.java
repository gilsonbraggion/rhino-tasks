package com.gilsonbraggion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
