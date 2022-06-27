package com.gilsonbraggion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.Liderado;

@Repository
public interface LideradoRepository extends JpaRepository<Liderado, Long> {

	public List<Liderado> findByIdUsuario(Long idUsuario);

}
