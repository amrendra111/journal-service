package com.apica.journal.service.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;

import com.apica.journal.service.exception.JournalServiceApiException;
import com.apica.journal.service.service.impl.JournalService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaMessageConsumer {

	@Autowired
	private JournalService journalService;

	private static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);

	@KafkaListener(topics = "user-events", groupId = "test-group-id")
	public void listen(String message) {
		try {
			log.info("Received message: " + message);
			KafkaMesage m = mapper.readValue(message, new TypeReference<KafkaMesage>() {
			});
			journalService.createJournalRecord(m);
		} catch (Exception e) {
			throw new JournalServiceApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
