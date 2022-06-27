package com.gilsonbraggion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.SubProjeto;

@Repository
public interface SubProjetoRepository extends JpaRepository<SubProjeto, Long> {
	
	public List<SubProjeto> findByIdUsuario(Long idUsuario);
	
}
