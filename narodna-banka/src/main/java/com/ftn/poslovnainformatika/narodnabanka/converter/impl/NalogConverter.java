package com.ftn.poslovnainformatika.narodnabanka.converter.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.impl.NalogDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.nalog.NalogDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.nalog.NalogViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Nalog;

@Component
public class NalogConverter implements DtoConverter<Nalog, NalogViewDTO, NalogDataDTO> {

	@Override
	public NalogViewDTO convertToDTO(Nalog source) {
		
		NalogDTO dto = new NalogDTO(source.getId(), source.getDuznik(), source.getPoverilac(), 
				source.getRacunDuznika(), source.getRacunPoverioca(), source.getSvrhaPlacanja(), 
				source.getIznos(), source.getSifraValute(), source.getDatum(), source.isHitno(), 
				source.getPozivNaBrojZaduzenja(), source.getPozivNaBrojOdobrenja(), 
				source.getModelZaduzenja(), source.getModelOdobrenja());
		
		return dto;
	}

	@Override
	public Set<NalogViewDTO> convertToDTO(Set<Nalog> sources) {

		Set<NalogViewDTO> result = new HashSet<>();
		for (Nalog jpa : sources) result.add(convertToDTO(jpa));
		return result;
	}
	
	@Override
	public Nalog convertToJPA(NalogDataDTO source) {
		
		Nalog jpa = new Nalog(null, source.getDuznik(), source.getPoverilac(), source.getRacunDuznika(), 
				source.getRacunPoverioca(), source.getSvrhaPlacanja(), source.getIznos(), 
				source.getSifraValute(), source.getDatum(), source.isHitno(), source.getPozivNaBrojZaduzenja(), 
				source.getPozivNaBrojOdobrenja(), source.getModelZaduzenja(), source.getModelOdobrenja(), null);
		
		return jpa;
	}

	@Override
	public Set<Nalog> convertToJPA(Set<NalogDataDTO> sources) {

		Set<Nalog> result = new HashSet<>();
		for (NalogDataDTO dto : sources) result.add(convertToJPA(dto));
		return result;
	}

}
