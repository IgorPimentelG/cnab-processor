package com.cnab.processor.domain;

import java.math.BigDecimal;
import java.util.List;

public record TransactionReport(
  String storeName,
  BigDecimal amount,
  List<TransactionOutput> transactions
) {

  public TransactionReport addAmount(BigDecimal amount) {
    return new TransactionReport(
      storeName, 
      this.amount.add(amount), 
      transactions
    );
  }

  public TransactionReport addTransaction(TransactionOutput transaction) {
    transactions.add(transaction);
    return new TransactionReport(storeName, amount, transactions);
  }
}
