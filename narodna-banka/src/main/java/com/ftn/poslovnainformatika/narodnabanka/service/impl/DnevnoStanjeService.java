package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ftn.poslovnainformatika.narodnabanka.dto.DnevnoStanjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.ObracunskiRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.DnevnoStanje;
import com.ftn.poslovnainformatika.narodnabanka.repository.DnevnoStanjeRepository;
import com.ftn.poslovnainformatika.narodnabanka.service.ObracunskiRacunService;
import com.ftn.poslovnainformatika.narodnabanka.service.PorukaService;

@Service
public class DnevnoStanjeService implements com.ftn.poslovnainformatika.narodnabanka.service.DnevnoStanjeService {
	
	@Autowired
	private ObracunskiRacunService racunService;
	
	@Autowired
	private DnevnoStanjeRepository dnevnoStanjeRepo;
	
	@Autowired
	private PorukaService porukaService;

	@Autowired
	private DtoConverter<DnevnoStanje, DnevnoStanjeDTO> dnevnoStanjeConverter;
	
	@Scheduled(cron = "@daily")
	public void createDnevnaStanja() {
		System.out.println("Creating dnevna stanja...");
		
		Set<ObracunskiRacunDTO> racuni = racunService.getAll();
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		
		Set<DnevnoStanje> novaStanja = new HashSet<>();
		
		for (ObracunskiRacunDTO racun : racuni) {
			Optional<DnevnoStanje> trenutnoStanje = dnevnoStanjeRepo.
					findByObracunskiRacun_BrojObracunskogRacunaAndDatum(racun.getBrojObracunskogRacuna(), today);
			
			if (trenutnoStanje.isPresent()) continue;
			
			Optional<DnevnoStanje> prethodnoStanje = dnevnoStanjeRepo.
					findByObracunskiRacun_BrojObracunskogRacunaAndDatum(racun.getBrojObracunskogRacuna(), yesterday);
			
			DnevnoStanje novoStanje = new DnevnoStanje();
			novoStanje.setDatum(today);
			novoStanje.setPrethodnoStanje(prethodnoStanje.isPresent() ? prethodnoStanje.get().getNovoStanje() : 0);
			novoStanje.setNovoStanje(novoStanje.getPrethodnoStanje());
			novoStanje.setObracunskiRacun(racunService.getReference(racun.getBrojObracunskogRacuna()));
			novaStanja.add(novoStanje);
		}
		
		dnevnoStanjeRepo.saveAll(novaStanja);
	}
	
	@PostConstruct
	public void checkDnevnaStanja() {
		System.out.println("Checking dnevna stanja...");
		
		createDnevnaStanja();
	}

	@Override
	public DnevnoStanjeDTO getOne(int id) {
		DnevnoStanje dnevnoStanje = dnevnoStanjeRepo.getById(id);

		return dnevnoStanjeConverter.convertToDTO(dnevnoStanje);
	}

	@Override
	public int create(DnevnoStanjeDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(int id, DnevnoStanjeDTO dto) {
		DnevnoStanje stanje = dnevnoStanjeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		stanje.setPrometNaTeret(dto.getPrometNaTeret());
		stanje.setPrometUKorist(dto.getPrometUKorist());
		stanje.setNovoStanje(dto.getNovoStanje());
		
		dnevnoStanjeRepo.save(stanje);
		
	}

	@Override
	public void delete(int id) {
		DnevnoStanje dnevnoStanje = dnevnoStanjeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());

		dnevnoStanjeRepo.deleteById(id);
	}

	@Override
	public DnevnoStanje getByBrojObracunskogRacunaAndDatum(String brojObracunskogRacuna, LocalDate datum) {
		DnevnoStanje stanje = dnevnoStanjeRepo.
				findByObracunskiRacun_BrojObracunskogRacunaAndDatum(brojObracunskogRacuna, datum).orElseThrow();
		
		return stanje;
	}
	
	@Override
	public void saveAll(Collection<DnevnoStanje> stanja) {
		dnevnoStanjeRepo.saveAll(stanja);
	}

	@Override
	public Set<PorukaDTO> getPorukeStanja(int id) {
		return porukaService.getByDnevnoStanjeId(id);
	}

}
