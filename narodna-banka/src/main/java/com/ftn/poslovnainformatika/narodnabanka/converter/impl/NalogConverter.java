package com.ftn.poslovnainformatika.narodnabanka.converter.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.NalogDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Nalog;

@Component
public class NalogConverter implements DtoConverter<Nalog, NalogDTO> {

	@Override
	public NalogDTO convertToDTO(Nalog source) {
		
		NalogDTO dto = new NalogDTO(source.getId(), source.getDuznik(), source.getPoverilac(), 
				source.getRacunDuznika(), source.getRacunPoverioca(), source.getSvrhaPlacanja(), 
				source.getIznos(), source.getSifraValute(), source.getDatum(), source.isHitno(), 
				source.getPozivNaBrojZaduzenja(), source.getPozivNaBrojOdobrenja(), 
				source.getModelZaduzenja(), source.getModelOdobrenja(), null);
		
		return dto;
	}

	@Override
	public Set<NalogDTO> convertToDTO(Set<Nalog> sources) {

		Set<NalogDTO> result = new HashSet<>();
		for (Nalog jpa : sources) result.add(convertToDTO(jpa));
		return result;
	}
	
	@Override
	public Nalog convertToJPA(NalogDTO source) {
		
		Nalog jpa = new Nalog(null, source.getDuznik(), source.getPoverilac(), source.getRacunDuznika(), 
				source.getRacunPoverioca(), source.getSvrhaPlacanja(), source.getIznos(), 
				source.getSifraValute(), source.getDatum(), source.isHitno(), source.getPozivNaBrojZaduzenja(), 
				source.getPozivNaBrojOdobrenja(), source.getModelZaduzenja(), source.getModelOdobrenja(), null);
		
		return jpa;
	}

	@Override
	public Set<Nalog> convertToJPA(Set<NalogDTO> sources) {

		Set<Nalog> result = new HashSet<>();
		for (NalogDTO dto : sources) result.add(convertToJPA(dto));
		return result;
	}

}
