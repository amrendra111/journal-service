package com.apica.journal.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apica.journal.service.reponse.JournalResponse;
import com.apica.journal.service.service.IJournalService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("journal")
@Api(tags = "Journal Controller")
public class JournalController {

	@Autowired
	private IJournalService journalService;

	@GetMapping("/list")
	public ResponseEntity<List<JournalResponse>> getAllJournals() {
		log.info("fetching all journals in controller...");
		List<JournalResponse> response = journalService.getAllJournals();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
