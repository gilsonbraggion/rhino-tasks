package com.gilsonbraggion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.AtividadeSubProjeto;

@Repository
public interface AtividadeSubProjetoRepository extends JpaRepository<AtividadeSubProjeto, Long> {

	@Query(value = "select atv from AtividadeSubProjeto atv where atv.subProjeto.id = :idSubProjeto and atv.idUsuario = :idUsuario order by atv.dataInicio ASC")
	public List<AtividadeSubProjeto> buscarAtividadesPorSubProjeto(@Param("idSubProjeto") Long idSubProjeto, @Param("idUsuario") Long idUsuario);

	public List<AtividadeSubProjeto> findByIdUsuario(Long idUsuario);
}
