package com.cnab.processor.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRANSACTIONS")
public record TransactionOutput(
	@Id
  String id,

  Integer type,

	@Column("REGISTERED_AT")
  LocalDateTime registeredAt,

  BigDecimal amount,
  String cpf,

	@Column("CARD_NUMBER")
  String cardNumber,

	@Column("STORE_HOLDER")
  String storeHolder,

	@Column("STORE_NAME")
  String storeName
) {
	public TransactionOutput withAmount(BigDecimal amount) {
		return new TransactionOutput(
		  this.id,
		  this.type,
		  this.registeredAt,
          amount,
          this.cpf,
          this.cardNumber,
          this.storeHolder,
		  this.storeName
		);
	}

	public TransactionOutput withRegisteredAt(String date, String hour) {
		var formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
		var registeredAt = LocalDateTime.parse(date + " " + hour, formatter);

		return new TransactionOutput(
		  this.id,
		  this.type,
		  registeredAt,
		  this.amount,
		  this.cpf,
		  this.cardNumber,
		  this.storeHolder,
		  this.storeName
		);
	}

	public TransactionOutput withCPF(String cpf) {
		var lastDigits = cpf.substring(9).length() == 1 ?
		  cpf.substring(9) + "0" : cpf.substring(9);
		var formattedCPF = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
		  cpf.substring(6, 9) + "-" + lastDigits;

		return new TransactionOutput(
		  this.id,
		  this.type,
		  this.registeredAt,
		  this.amount,
		  formattedCPF,
		  this.cardNumber,
		  this.storeHolder,
		  this.storeName
		);
	}
}
