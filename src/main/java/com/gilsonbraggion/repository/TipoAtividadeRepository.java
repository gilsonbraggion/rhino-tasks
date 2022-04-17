package com.gilsonbraggion.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.TipoAtividade;

@Repository
public interface TipoAtividadeRepository extends JpaRepository<TipoAtividade, Long> {

	@Override
	@Cacheable("tipoAtividades")
	public List<TipoAtividade> findAll();
}
