package com.cnab.processor.infra.controller;

import com.cnab.processor.infra.services.CnabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cnab")
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
