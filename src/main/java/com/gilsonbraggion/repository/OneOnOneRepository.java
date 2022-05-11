package com.gilsonbraggion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.Liderado;
import com.gilsonbraggion.model.OneOnOne;

@Repository
public interface OneOnOneRepository extends JpaRepository<OneOnOne, Long> {

	public List<OneOnOne> findByOrderByDataAsc();

	public List<OneOnOne> findByLideradoOrderByDataAsc(Liderado liderado);

}
