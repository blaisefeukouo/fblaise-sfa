package com.example.fblaisesfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fblaisesfa.model.Sector;

@Repository("sectorRepository")
public interface SectorRepository extends JpaRepository<Sector, Long> {

	Sector findById(Long id);

}
