package com.ftn.poslovnainformatika.narodnabanka.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Nalog;

public interface NalogRepository extends JpaRepository<Nalog, Integer> {
	
	Page<Nalog> findByPoruka_Id(int porukaId, Pageable pageable);

}
