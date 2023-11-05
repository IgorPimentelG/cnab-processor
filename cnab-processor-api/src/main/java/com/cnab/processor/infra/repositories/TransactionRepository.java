package com.cnab.processor.infra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cnab.processor.domain.TransactionOutput;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionOutput, String> {
  List<TransactionOutput> findAllByOrderByStoreNameAsc();
}
