package com.cnab.processor.domain;

import java.math.BigDecimal;

public enum TypeTransaction {
  DEBIT(1), INVOICE(2), FINANCING(3), 
  CREDIT(4), LOAN_RECEIPT(5), SALES(6),
  TED_RECEIPT(7), DOC_RECEIPT(8), RENT(9);

  private int type;

  private TypeTransaction(int type) {
    this.type = type;
  }

  public BigDecimal getSignal() {
    return switch(type) {
      case 1, 4, 5, 6, 7, 8 -> BigDecimal.ONE;
      case 2, 3, 9 -> BigDecimal.valueOf(-1);
      default -> BigDecimal.ZERO;
    };
  }

  public static TypeTransaction findByType(int type) {
    for (TypeTransaction typeTransaction : values()) {
      if (typeTransaction.type == type) {
        return typeTransaction;
      }
    }
    throw new RuntimeException("Invalid type: " + type);
  }
}
