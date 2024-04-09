package com.apica.journal.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.apica.journal.service.entity.Journal;
import com.apica.journal.service.exception.JournalServiceApiException;
import com.apica.journal.service.kafka.consumer.KafkaMesage;
import com.apica.journal.service.reponse.JournalResponse;
import com.apica.journal.service.repository.JournalRepository;
import com.apica.journal.service.service.IJournalService;

@Service
public class JournalService implements IJournalService {

	@Autowired
	private JournalRepository journalRepository;

	@Override
	public List<JournalResponse> getAllJournals() {
		try {
			List<JournalResponse> response = new ArrayList<>();
			journalRepository.findAll().forEach(t -> {
				response.add(new JournalResponse(t.getId(), t.getTimestamp(), t.getUserId(), t.getEventType()));
			});
			return response;
		} catch (Exception e) {
			throw new JournalServiceApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public Integer createJournalRecord(KafkaMesage m) {
		try {
			Journal journal = new Journal();
			journal.setUserId(m.getUserId());
			journal.setEventType(m.getEventType());
			journal.setTimestamp(m.getTimeStamp());
			Journal savedItem = journalRepository.save(journal);
			return savedItem.getId();
		} catch (Exception e) {
			throw new JournalServiceApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
