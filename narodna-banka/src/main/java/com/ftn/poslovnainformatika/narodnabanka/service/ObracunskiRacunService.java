package com.ftn.poslovnainformatika.narodnabanka.service;

import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.ObracunskiRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.ObracunskiRacun;

public interface ObracunskiRacunService {
	
	Set<ObracunskiRacunDTO> getAll();
	
	ObracunskiRacun getReference(String brojRacuna);

}
