package com.ftn.poslovnainformatika.narodnabanka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Nalog;

import java.util.List;

public interface NalogRepository extends JpaRepository<Nalog, Integer> {

	List<Nalog> findByPoruka_Id(int porukaId);

}
