package com.apica.journal.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apica.journal.service.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

	Privilege findByName(String name);

}
