package com.gilsonbraggion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.Atividade;
import com.gilsonbraggion.model.TipoAtividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

	public List<Atividade> findByOrderByDataExecucaoAsc();

	public List<Atividade> findByTipoAtividadeOrderByDataExecucaoAsc(TipoAtividade tipoAtividade);

}
