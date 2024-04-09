package com.apica.journal.service.config;

import com.apica.journal.service.model.SqlDbCredential;

public interface IPropertyConfiguration {

	String getEnv();

	SqlDbCredential getSqlDbCredential();

}
