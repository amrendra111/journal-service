package com.apica.journal.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SqlDbCredential {
	private String url;
	private String userName;
	private String password;
	private String maxPoolSize;

}
