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

	public List<OneOnOne> findByOrderByDataAsc();

	public List<OneOnOne> findByLideradoOrderByDataAsc(Liderado liderado);

	@Query("select distinct new com.gilsonbraggion.bean.FiltroDatas(month(feed.data), year(feed.data)) from OneOnOne as feed")
	public List<FiltroDatas> buscarDatas();

	@Query("select feed from OneOnOne feed where month(feed.data) = :mes and year(feed.data) = :ano")
	public List<OneOnOne> buscarOneonOnePorData(@Param("mes") int mes, @Param("ano") int ano);

	@Query("select feed from OneOnOne feed where realizado = 0 order by data ASC")
	public List<OneOnOne> buscarNaoRealizados();
	
	
}
