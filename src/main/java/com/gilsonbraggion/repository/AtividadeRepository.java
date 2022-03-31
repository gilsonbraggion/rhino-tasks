package com.gilsonbraggion.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.User;

@Repository
public interface AtividadeRepository extends JpaRepository<User, UUID> {

}
