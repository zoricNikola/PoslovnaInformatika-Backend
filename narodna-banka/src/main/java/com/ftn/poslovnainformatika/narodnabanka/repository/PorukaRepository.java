package com.ftn.poslovnainformatika.narodnabanka.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Kliring;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaPoruke;

public interface PorukaRepository extends JpaRepository<Poruka, Integer> {
	
	Page<Poruka> findByDnevnoStanje_Id(int dnevnoStanjeId, Pageable pageable);
	
	List<Poruka> findByVrstaPorukeAndKliring(VrstaPoruke vrstaPoruke, Kliring kliring);

}
