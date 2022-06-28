package com.gilsonbraggion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.Projeto;
import com.gilsonbraggion.model.SubProjeto;

@Repository
public interface SubProjetoRepository extends JpaRepository<SubProjeto, Long> {
	
	public List<SubProjeto> findByIdUsuario(Long idUsuario);

	public List<SubProjeto> findByProjetoAndIdUsuario(Projeto projeto, Long idUsuario);
	
	@Query("select sb from SubProjeto sb, ProjetoCompartilhado pc where (sb.idUsuario = :idUsuarioLogado or pc.idInvited = :idUsuarioLogado) and sb.id = pc.idSubProjeto")
	public List<SubProjeto> buscarSubrojetosMeusCompartilhados(@Param("idUsuarioLogado") Long idUsuarioLogado); 

}
