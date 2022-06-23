package com.gilsonbraggion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsonbraggion.model.AtividadeSubProjeto;

@Repository
public interface AtividadeSubProjetoRepository extends JpaRepository<AtividadeSubProjeto, Long> {

}