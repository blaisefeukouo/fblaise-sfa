package com.example.fblaisesfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fblaisesfa.model.Client;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findById(Long id);

}
