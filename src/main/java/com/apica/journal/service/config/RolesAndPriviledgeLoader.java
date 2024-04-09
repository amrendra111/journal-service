package com.apica.journal.service.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.apica.journal.service.entity.Privilege;
import com.apica.journal.service.entity.Role;
import com.apica.journal.service.entity.User;
import com.apica.journal.service.repository.PrivilegeRepository;
import com.apica.journal.service.repository.RoleRepository;
import com.apica.journal.service.repository.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class RolesAndPriviledgeLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;
		List<Privilege> priviledges = privilegeRepository.findAll();
		if (priviledges.isEmpty())
			createPriviledges();
		List<Role> roles = roleRepository.findAll();
		if (roles.isEmpty())
			createRoles();

		if (!userRepository.existsByEmail("admin@mail.com")) {
			User admin = new User();
			admin.setFirstName("admin");
			admin.setLastName("");
			// an encoder can be used here
			admin.setPassword(passwordEncoder.encode("admin_password"));
			admin.setEmail("admin@mail.com");
			Role adminRole = roleRepository.findByName("ROLE_ADMIN");
			admin.setRoles(new HashSet<>(Arrays.asList(adminRole)));
			admin.setEnabled(true);

			User user = new User();
			user.setFirstName("user");
			user.setLastName("");
			// an encoder can be used here
			user.setPassword(passwordEncoder.encode("user_password"));
			user.setEmail("user@mail.com");
			Role userRole = roleRepository.findByName("ROLE_USER");
			user.setRoles(new HashSet<>(Arrays.asList(userRole)));
			user.setEnabled(true);
			userRepository.saveAll(Arrays.asList(admin, user));
		}
		alreadySetup = true;
	}

	@Transactional
	private void createRoles() {
		List<Privilege> priviledges = privilegeRepository.findAll();
		Role admin = new Role();
		admin.setName("ROLE_ADMIN");
		admin.setPrivileges(new HashSet<>(priviledges));

		Role manager = new Role();
		manager.setName("ROLE_MANAGER");
		manager.setPrivileges(new HashSet<>(priviledges));

		Role user = new Role();
		user.setName("ROLE_USER");
		user.setPrivileges(Collections.emptySet());

		roleRepository.saveAll(Arrays.asList(admin, manager, user));

	}

	@Transactional
	private void createPriviledges() {
		Privilege read = new Privilege();
		read.setName("READ_PRIVILEDGE");

		Privilege write = new Privilege();
		write.setName("WRITE_PRIVILEDGE");

		privilegeRepository.saveAll(Arrays.asList(write, read));
	}

}
