package com.ibm.countrycodes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ibm.countrycodes.model.Country;

public interface CountryCodesRepository extends JpaRepository<Country, Long> {

	Page<Country> findAll(Pageable pageable);

	@Query("SELECT c FROM Country c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', ?1,'%')) or LOWER(c.iso3Code) LIKE LOWER(CONCAT('%', ?1,'%'))")
	Page<Country> findByNameOriso3CodeLikeCaseInsensitive(String name, Pageable pageable);

}
