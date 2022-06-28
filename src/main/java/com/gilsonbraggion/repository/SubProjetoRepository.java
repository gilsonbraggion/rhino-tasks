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
	
	@Query(" SELECT sb FROM SubProjeto sb LEFT JOIN ProjetoCompartilhado pc ON pc.idSubProjeto = sb.id where sb.idUsuario = :idUsuarioLogado or pc.idInvited = :idUsuarioLogado")
	public List<SubProjeto> buscarSubrojetosMeusCompartilhados(@Param("idUsuarioLogado") Long idUsuarioLogado); 

}
