package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	@Override
	public Set<ObracunskiRacunDTO> getAll() {
		List<ObracunskiRacun> racuni = racunRepo.findAll();
		
		return racunConverter.convertToDTO(new HashSet<>(racuni));
	}
	
	@Override
	public ObracunskiRacun getReference(String brojRacuna) {
		return racunRepo.getById(brojRacuna);
	}

}
