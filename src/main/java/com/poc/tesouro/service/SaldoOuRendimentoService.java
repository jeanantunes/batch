package com.poc.tesouro.service;

import com.poc.tesouro.model.SaldoOuRendimento;
import com.poc.tesouro.repository.SaldoOuRendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaldoOuRendimentoService {

    @Autowired
    SaldoOuRendimentoRepository repository;

    public List<SaldoOuRendimento> getAllSaldoOuRendimentos() {
        List<SaldoOuRendimento> userList = repository.findAll();

        if (userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<SaldoOuRendimento>();
        }
    }

    public ResponseEntity getSaldoOuRendimentoById(Long id) throws Exception {
        Optional<SaldoOuRendimento> user = repository.findById(id);

        if (user.isPresent()) {
            return user.map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        } else {
            throw new Exception("SaldoOuRendimento não localizado!!");
            //new ResponseEntity<>("SaldoOuRendimento não localizado!!!", HttpStatus.NOT_FOUND)
        }
    }

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 100, maxDelay = 500)) //Retryable in Service
    public ResponseEntity createOrUpdateSaldoOuRendimento(SaldoOuRendimento entity) throws Exception {
        Optional<SaldoOuRendimento> user = repository.findById(entity.getId());

        if (user.isPresent()) {
            SaldoOuRendimento newEntity = user.get();
            //newEntity.setFontePagadoraId(entity.getFontePagadoraId());
            //newEntity.setClienteId(entity.getClienteId());
            //newEntity.setCoTitularesId(entity.getCoTitularesId());

            //newEntity.setFontesPagadoras(entity.getFontesPagadoras());
            newEntity.setTitular(entity.getTitular());
            newEntity.setCoTitulares(entity.getCoTitulares());
            newEntity.setNmConta(entity.getNmConta());
            newEntity.setNumeroDaConta(entity.getNumeroDaConta());
            newEntity.setVrSaldoAnterior(entity.getVrSaldoAnterior());
            newEntity.setVrSaldoAtual(entity.getVrSaldoAtual());
            newEntity.setDtApuracao(entity.getDtApuracao());
            newEntity.setLegadoOrigem(entity.getLegadoOrigem());
            newEntity.setProduto(entity.getProduto());
            newEntity.setCategoriaRendto(entity.getCategoriaRendto());
            newEntity.setVrRendto(entity.getVrRendto());
            newEntity = repository.save(newEntity);

            return ResponseEntity.ok().build();
        } else {
            entity = repository.save(entity);

            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity<String> deleteSaldoOuRendimentoById(Long id) throws Exception {
        ResponseEntity<String> user = repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return new ResponseEntity<String>("SaldoOuRendimento deletado com sucesso!!!", HttpStatus.OK);
                }).orElse(new ResponseEntity<>("SaldoOuRendimento não localizado!!!", HttpStatus.NOT_FOUND));

        if (user.getStatusCode().is2xxSuccessful()) {
            //repository.deleteById(id);
            new ResponseEntity<>("{}", HttpStatus.OK);
        } else {
            new ResponseEntity<String>("SaldoOuRendimento não encontrado com id = ", HttpStatus.NOT_FOUND);
        }
        return user;
    }
}