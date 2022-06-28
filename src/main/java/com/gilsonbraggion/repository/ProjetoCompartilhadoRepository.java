package com.gilsonbraggion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.ProjetoCompartilhado;

@Repository
public interface ProjetoCompartilhadoRepository extends JpaRepository<ProjetoCompartilhado, Long> {

	@Query(value = "select pc from ProjetoCompartilhado pc where idOwner = :idUsuarioLogado or idInvited = :idUsuarioLogado")
	public List<ProjetoCompartilhado> buscarProjetosCompartilhados(@Param("idUsuarioLogado") Long idUsuarioLogado);
	
	public ProjetoCompartilhado findByIdInvitedAndIdSubProjeto(Long idConvidado, Long idSubProjeto);

}
