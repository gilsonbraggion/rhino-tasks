package com.gilsonbraggion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	
	@Query("select e from Usuario e where e.id <> :idUsuarioLogado")
	List<Usuario> buscarUsuariosForaLogado(@Param("idUsuarioLogado") Long idUsuarioLogado);

}
