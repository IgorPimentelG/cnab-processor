package com.cnab.processor.infra.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnab.processor.domain.TransactionReport;
import com.cnab.processor.domain.TypeTransaction;
import com.cnab.processor.infra.repositories.TransactionRepository;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepository repository;

  public List<TransactionReport> listTransactionsByStoreName() {
    var transactions = repository.findAllByOrderByStoreNameAsc();
    var reports = new LinkedHashMap<String, TransactionReport>();

    transactions.forEach(transaction -> {
      var storeName = transaction.storeName();
      var typeTransaction = TypeTransaction.findByType(transaction.type());
      var amount = transaction.amount().multiply(typeTransaction.getSignal());

      reports.compute(storeName,(key, existingReport) -> {
        var report = (existingReport != null) ? 
          existingReport : new TransactionReport(key, BigDecimal.ZERO, new ArrayList<>());

        return report.addAmount(amount)
          .addTransaction(transaction.withAmount(amount));
      });
    });

    return new ArrayList<>(reports.values());
  }
  
}
