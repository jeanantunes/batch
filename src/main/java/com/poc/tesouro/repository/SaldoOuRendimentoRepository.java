package com.poc.tesouro.repository;

import com.poc.tesouro.model.SaldoOuRendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaldoOuRendimentoRepository extends JpaRepository<SaldoOuRendimento, Long> {

}
