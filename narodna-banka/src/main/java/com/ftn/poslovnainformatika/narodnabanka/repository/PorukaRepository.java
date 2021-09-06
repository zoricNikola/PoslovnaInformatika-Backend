package com.ftn.poslovnainformatika.narodnabanka.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Kliring;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaPoruke;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PorukaRepository extends JpaRepository<Poruka, Integer> {
	
	//Page<Poruka> findByDnevnoStanje_Id(int dnevnoStanjeId, Pageable pageable);
	
	List<Poruka> findByVrstaPorukeAndKliring(VrstaPoruke vrstaPoruke, Kliring kliring);

	@Query(value = "Select p from Poruka p WHERE " +
			"p.bankaDuznika.sifraBanke = :bankaId OR " +
			"p.bankaPoverioca.sifraBanke = :bankaId AND " +
			"(:startDatum is null OR p.datum >= :startDatum) AND " +
			"(:endDatum is null OR p.datum <= :endDatum)")
	List<Poruka> filterPoruke(@Param("bankaId") int bankaId,
							  @Param("startDatum")LocalDate startDatum,
							  @Param("endDatum") LocalDate endDatum);
}
