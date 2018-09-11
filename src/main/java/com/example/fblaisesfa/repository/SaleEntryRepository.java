package com.example.fblaisesfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fblaisesfa.model.Product;
import com.example.fblaisesfa.model.Sale;
import com.example.fblaisesfa.model.SaleEntry;

@Repository("saleEntryRepository")
public interface SaleEntryRepository extends JpaRepository<SaleEntry, Long> {

	SaleEntry findById(Long id);

	SaleEntry findByProductAndSale(Product product, Sale sale);

}
