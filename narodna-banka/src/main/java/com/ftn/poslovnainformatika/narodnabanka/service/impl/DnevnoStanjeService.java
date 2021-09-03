package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ftn.poslovnainformatika.narodnabanka.dto.ObracunskiRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.DnevnoStanje;
import com.ftn.poslovnainformatika.narodnabanka.repository.DnevnoStanjeRepository;
import com.ftn.poslovnainformatika.narodnabanka.service.ObracunskiRacunService;

@Service
public class DnevnoStanjeService {
	
	@Autowired
	private ObracunskiRacunService racunService;
	
	@Autowired
	private DnevnoStanjeRepository dnevnoStanjeRepo;
	
	@Scheduled(cron = "@daily")
	public void createDnevnaStanja() {
		System.out.println("Creating dnevna stanja...");
		
		Set<ObracunskiRacunDTO> racuni = racunService.getAll();
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		
		Set<DnevnoStanje> novaStanja = new HashSet<>();
		
		for (ObracunskiRacunDTO racun : racuni) {
			Optional<DnevnoStanje> prethodnoStanje = dnevnoStanjeRepo.
					findByObracunskiRacun_BrojObracunskogRacunaAndDatum(racun.getBrojObracunskogRacuna(), yesterday);
			
			DnevnoStanje novoStanje = new DnevnoStanje();
			novoStanje.setDatum(today);
			novoStanje.setPrethodnoStanje(prethodnoStanje.isPresent() ? prethodnoStanje.get().getNovoStanje() : 0);
			novoStanje.setNovoStanje(prethodnoStanje.isPresent() ? prethodnoStanje.get().getNovoStanje() : 0);
			novoStanje.setObracunskiRacun(racunService.getReference(racun.getBrojObracunskogRacuna()));
			novaStanja.add(novoStanje);
		}
		
		dnevnoStanjeRepo.saveAll(novaStanja);
	}

}
