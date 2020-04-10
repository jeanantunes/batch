package com.poc.tesouro.repository;

import com.poc.tesouro.model.CoTitulares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoTitularesRepository extends JpaRepository<CoTitulares, Long> {

}
