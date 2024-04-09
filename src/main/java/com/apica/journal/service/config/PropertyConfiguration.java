package com.apica.journal.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.apica.journal.service.model.SqlDbCredential;

@Profile("!local")
//@Configuration
public class PropertyConfiguration implements IPropertyConfiguration {

	@Autowired
	private Environment environment;

	@Override
	public String getEnv() {
		return getSpringProfile();
	}

	private String getSpringProfile() {
		return this.environment.getActiveProfiles()[0];
	}

	@Override
	public SqlDbCredential getSqlDbCredential() {
		// fetching the credentials stored in cloud service
		return null;
	}

}
