package com.example.fblaisesfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fblaisesfa.model.ProductFamily;

@Repository("productFamilyRepository")
public interface ProductFamilyRepository extends JpaRepository<ProductFamily, Long> {

	ProductFamily findById(Long id);

}
