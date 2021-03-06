package com.poc.tesouro.repository;

import com.poc.tesouro.model.FontePagadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FontePagadoraRepository extends JpaRepository<FontePagadora, Long> {

}
