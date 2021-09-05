package com.ftn.poslovnainformatika.narodnabanka.converter.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.DnevnoStanjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.KliringDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.NalogDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.DnevnoStanje;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Kliring;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Nalog;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.PoslovnaBankaRepository;

@Component
public class PorukaConverter implements DtoConverter<Poruka, PorukaDTO> {

	@Autowired
	private DtoConverter<Nalog, NalogDTO> nalogConverter;
	
	@Autowired
	private DtoConverter<PoslovnaBanka, PoslovnaBankaDTO> poslovnaBankaConverter;
	
	@Autowired
	private DtoConverter<DnevnoStanje, DnevnoStanjeDTO> dnevnoStanjeConverter;
	
	@Autowired
	private DtoConverter<Kliring, KliringDTO> kliringConverter;
	
	@Autowired
	private PoslovnaBankaRepository poslovnaBankaRepo;
	
	@Override
	public PorukaDTO convertToDTO(Poruka source) {

		PorukaDTO dto = new PorukaDTO(source.getId(), source.getDatum(), source.getVrstaPoruke(), 
				source.getUkupanIznos(), source.getSifraValute(), source.getDatumValute(), 
				poslovnaBankaConverter.convertToDTO(source.getBankaDuznika()), 
				poslovnaBankaConverter.convertToDTO(source.getBankaPoverioca()), 
				dnevnoStanjeConverter.convertToDTO(source.getDnevnoStanjeBankeDuznika()), 
				dnevnoStanjeConverter.convertToDTO(source.getDnevnoStanjeBankePoverioca()), 
				kliringConverter.convertToDTO(source.getKliring()), 
				nalogConverter.convertToDTO(source.getNalozi()));
		
		return dto;
	}

	@Override
	public Set<PorukaDTO> convertToDTO(Set<Poruka> sources) {

		Set<PorukaDTO> result = new HashSet<>();
		for (Poruka jpa : sources) result.add(convertToDTO(jpa));
		return result;
	}

	@Override
	public Poruka convertToJPA(PorukaDTO source) {
		
		Poruka jpa = new Poruka(null, source.getDatum(), source.getVrstaPoruke(), source.getUkupanIznos(), 
				source.getSifraValute(), source.getDatumValute(), 
				poslovnaBankaRepo.getById(source.getBankaDuznika().getSifraBanke()), 
				poslovnaBankaRepo.getById(source.getBankaPoverioca().getSifraBanke()), null, null, null, null);
		
		Set<Nalog> nalozi = new HashSet<>();
		for (NalogDTO dto : source.getNalozi()) {
			Nalog nalog = nalogConverter.convertToJPA(dto);
			nalog.setPoruka(jpa);
			nalozi.add(nalog);
		}
		jpa.setNalozi(nalozi);
		
		return jpa;
	}

	@Override
	public Set<Poruka> convertToJPA(Set<PorukaDTO> sources) {

		Set<Poruka> result = new HashSet<>();
		for (PorukaDTO dto : sources) result.add(convertToJPA(dto));
		return result;
	}

}
