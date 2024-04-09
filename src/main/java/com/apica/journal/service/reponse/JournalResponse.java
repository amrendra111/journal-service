package com.apica.journal.service.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JournalResponse {
	private Integer id;
	private String timestamp;
	private Integer userId;
	private String eventType;
}
