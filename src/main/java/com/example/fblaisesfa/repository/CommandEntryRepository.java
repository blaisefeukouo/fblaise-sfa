package com.example.fblaisesfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fblaisesfa.model.Command;
import com.example.fblaisesfa.model.CommandEntry;
import com.example.fblaisesfa.model.Product;

@Repository("commandEntryRepository")
public interface CommandEntryRepository extends JpaRepository<CommandEntry, Long> {

	CommandEntry findById(Long id);

	CommandEntry findByProductAndCommand(Product product, Command command);

}
