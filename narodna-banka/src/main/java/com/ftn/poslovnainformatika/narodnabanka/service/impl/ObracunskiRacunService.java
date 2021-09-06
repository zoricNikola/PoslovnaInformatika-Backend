package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.DnevnoStanjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.StanjeObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.DnevnoStanje;
import com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.PoslovnaBankaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.ObracunskiRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.ObracunskiRacun;
import com.ftn.poslovnainformatika.narodnabanka.repository.ObracunskiRacunRepository;

@Service
public class ObracunskiRacunService implements com.ftn.poslovnainformatika.narodnabanka.service.ObracunskiRacunService {
	
	@Autowired
	private ObracunskiRacunRepository racunRepo;
	
	@Autowired
	private DtoConverter<ObracunskiRacun, ObracunskiRacunDTO> racunConverter;

	@Autowired
	private DtoConverter<DnevnoStanje, DnevnoStanjeDTO> dnevnoStanjeConverter;

	@Autowired
	private PoslovnaBankaService poslovnaBankaService;

	@Autowired
	private DnevnoStanjeService dnevnoStanjeService;
	
	@Override
	public Set<ObracunskiRacunDTO> getAll() {
		List<ObracunskiRacun> racuni = racunRepo.findAll();
		
		return racunConverter.convertToDTO(new HashSet<>(racuni));
	}
	
	@Override
	public ObracunskiRacun getReference(String brojRacuna) {
		return racunRepo.getById(brojRacuna);
	}

	@Override
	public List<StanjeObracunskogRacunaDTO> getStanjaObracunskihRacuna(LocalDate datum) {
		List<StanjeObracunskogRacunaDTO> stanja = new ArrayList<>();
		Set<PoslovnaBankaDTO> banke = poslovnaBankaService.getAll();

		banke.forEach(poslovnaBankaDTO -> {
			DnevnoStanje dnevnoStanje = dnevnoStanjeService.
					getByBrojObracunskogRacunaAndDatum(poslovnaBankaDTO.getObracunskiRacun().getBrojObracunskogRacuna(), datum);

			if(dnevnoStanje != null) {
				StanjeObracunskogRacunaDTO stanje = new StanjeObracunskogRacunaDTO();
				stanje.setPoslovnaBanka(poslovnaBankaDTO);
				stanje.setDnevnoStanje(dnevnoStanjeConverter.convertToDTO(dnevnoStanje));
				stanja.add(stanje);
			}
		});
		return stanja;
	}

}
