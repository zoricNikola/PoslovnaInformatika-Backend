package com.ftn.poslovnainformatika.narodnabanka.converter.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.impl.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.nalog.NalogDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.nalog.NalogViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poruka.PorukaDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poruka.PorukaViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Nalog;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.PoslovnaBankaRepository;

@Component
public class PorukaConverter implements DtoConverter<Poruka, PorukaViewDTO, PorukaDataDTO> {

	@Autowired
	private DtoConverter<Nalog, NalogViewDTO, NalogDataDTO> nalogConverter;
	
	@Autowired
	private DtoConverter<PoslovnaBanka, PoslovnaBankaViewDTO, PoslovnaBankaDataDTO> poslovnaBankaConverter;
	
	@Autowired
	private PoslovnaBankaRepository poslovnaBankaRepo;
	
	@Override
	public PorukaViewDTO convertToDTO(Poruka source) {

		PorukaDTO dto = new PorukaDTO(source.getId(), source.getDatum(), source.getVrstaPoruke(), 
				source.getUkupanIznos(), source.getSifraValute(), source.getDatumValute(), 
				poslovnaBankaConverter.convertToDTO(source.getBankaDuznika()), 
				poslovnaBankaConverter.convertToDTO(source.getBankaPoverioca()), 
				nalogConverter.convertToDTO(source.getNalozi()));
		
		return dto;
	}

	@Override
	public Set<PorukaViewDTO> convertToDTO(Set<Poruka> sources) {

		Set<PorukaViewDTO> result = new HashSet<>();
		for (Poruka jpa : sources) result.add(convertToDTO(jpa));
		return result;
	}

	@Override
	public Poruka convertToJPA(PorukaDataDTO source) {
		
		Poruka jpa = new Poruka(null, source.getDatum(), source.getVrstaPoruke(), source.getUkupanIznos(), 
				source.getSifraValute(), source.getDatumValute(), 
				poslovnaBankaRepo.getById(source.getBankaDuznika().getId()), 
				poslovnaBankaRepo.getById(source.getBankaPoverioca().getId()), null, null);
		
		Set<Nalog> nalozi = new HashSet<>();
		for (NalogViewDTO dto : source.getNalozi()) {
			Nalog nalog = nalogConverter.convertToJPA(dto);
			nalog.setPoruka(jpa);
			nalozi.add(nalog);
		}
		jpa.setNalozi(nalozi);
		
		return jpa;
	}

	@Override
	public Set<Poruka> convertToJPA(Set<PorukaDataDTO> sources) {

		Set<Poruka> result = new HashSet<>();
		for (PorukaDataDTO dto : sources) result.add(convertToJPA(dto));
		return result;
	}

}
