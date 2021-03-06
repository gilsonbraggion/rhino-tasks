package com.gilsonbraggion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.Atividade;
import com.gilsonbraggion.model.TipoAtividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

	public List<Atividade> findByIdUsuarioOrderByDataExecucaoAsc(Long idUsuario);

	public List<Atividade> findByTipoAtividadeAndIdUsuarioOrderByDataExecucaoAsc(TipoAtividade tipoAtividade, Long idUsuario);

	@Query(value = "select atv from Atividade atv, TipoAtividade tp where atv.tipoAtividade.id = tp.id and tp.id = :idTipoAtividade  and (atv.finalizado = 0 or atv.finalizado is null ) and atv.idUsuario = :idUsuario order by atv.dataExecucao ASC")
	public List<Atividade> buscarAtividadesPorTipoAtividadeAtivos(@Param("idTipoAtividade") Long tipoAtividade, @Param("idUsuario") Long idUsuario);

	public List<Atividade> findByIdUsuario(Long idUsuario);

}
