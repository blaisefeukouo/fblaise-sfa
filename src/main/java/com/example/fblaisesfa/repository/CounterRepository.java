package com.example.fblaisesfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fblaisesfa.model.Counter;

@Repository("counterRepository")
public interface CounterRepository extends JpaRepository<Counter, Long> {

	Counter findById(Long id);

}
