package com.cnab.processor.infra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnab.processor.domain.TransactionReport;
import com.cnab.processor.infra.services.TransactionService;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

  @Autowired
  private TransactionService service;

  @GetMapping
  ResponseEntity<List<TransactionReport>> listAll() {
    var result = service.listTransactionsByStoreName();
    return ResponseEntity.ok(result);
  }
}
