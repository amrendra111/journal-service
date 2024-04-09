package com.apica.journal.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apica.journal.service.entity.Journal;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {

}
