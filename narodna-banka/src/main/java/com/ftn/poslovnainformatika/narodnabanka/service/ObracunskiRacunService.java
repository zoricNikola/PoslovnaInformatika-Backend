package com.ftn.poslovnainformatika.narodnabanka.service;

import java.time.LocalDate;
import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.ObracunskiRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.IzvodObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.StanjeObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.ObracunskiRacun;

public interface ObracunskiRacunService {
	
	Set<ObracunskiRacunDTO> getAll();
	
	ObracunskiRacun getReference(String brojRacuna);

	Set<StanjeObracunskogRacunaDTO> getStanjaObracunskihRacuna(LocalDate datum);

	IzvodObracunskogRacunaDTO getIzvodObracunskogRacuna(int bankaId, LocalDate startDatum, LocalDate endDatum);
}
