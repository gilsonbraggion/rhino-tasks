package com.gilsonbraggion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.bean.FiltroDatas;
import com.gilsonbraggion.model.Liderado;
import com.gilsonbraggion.model.OneOnOne;

@Repository
public interface OneOnOneRepository extends JpaRepository<OneOnOne, Long> {

	
	public List<OneOnOne> findByIdUsuarioOrderByDataAsc(Long idUsuario);

	public List<OneOnOne> findByLideradoAndIdUsuarioOrderByDataAsc(Liderado liderado, Long idUsuario);

	@Query("select distinct new com.gilsonbraggion.bean.FiltroDatas(month(feed.data), year(feed.data)) from OneOnOne as feed where feed.idUsuario = :idUsuario")
	public List<FiltroDatas> buscarDatas(@Param("idUsuario") Long idUsuario);

	@Query("select feed from OneOnOne feed where month(feed.data) = :mes and year(feed.data) = :ano and feed. idUsuario = :idUsuario")
	public List<OneOnOne> buscarOneonOnePorData(@Param("mes") int mes, @Param("ano") int ano, @Param("idUsuario") Long idUsuario);

	@Query("select feed from OneOnOne feed where realizado = 0 and feed.idUsuario = :idUsuario order by data ASC")
	public List<OneOnOne> buscarNaoRealizados(@Param("idUsuario") Long idUsuario);

	public List<OneOnOne> findByIdUsuario(Long idUsuario);
}
