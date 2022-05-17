package com.gilsonbraggion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

}
