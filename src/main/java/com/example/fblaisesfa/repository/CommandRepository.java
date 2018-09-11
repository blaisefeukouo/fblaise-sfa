package com.example.fblaisesfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fblaisesfa.model.Command;

@Repository("commandRepository")
public interface CommandRepository extends JpaRepository<Command, Long> {

	Command findById(Long id);

}
