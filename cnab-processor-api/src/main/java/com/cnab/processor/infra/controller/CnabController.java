package com.cnab.processor.infra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cnab.processor.infra.services.CnabService;

@RestController
@RequestMapping("/cnab")
@CrossOrigin(origins = "*")
public class CnabController {

	@Autowired
	private CnabService service;

	@PostMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file)
			throws Exception {
		service.uploadFile(file);
		return ResponseEntity.ok("Start processing file...");
	}
}
