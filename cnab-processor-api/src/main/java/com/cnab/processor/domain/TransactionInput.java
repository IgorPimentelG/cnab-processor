package com.cnab.processor.domain;

import java.math.BigDecimal;

public record TransactionInput(
  Integer type,
  String data,
  BigDecimal amount,
  Long cpf,
  String cardNumber,
  String hour,
  String storeHolder,
  String storeName
) {}
