package com.example.fblaisesfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fblaisesfa.model.Sale;

@Repository("saleRepository")
public interface SaleRepository extends JpaRepository<Sale, Long> {

	Sale findById(Long id);

}
