package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poruka.PorukaDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poruka.PorukaViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.repository.PorukaRepository;

@Service
public class PorukaService implements com.ftn.poslovnainformatika.narodnabanka.service.PorukaService {

	@Autowired
	private PorukaRepository porukaRepo;
	
	@Autowired
	private DtoConverter<Poruka, PorukaViewDTO, PorukaDataDTO> porukaConverter;
	
	@Override
	public PorukaViewDTO getOne(int id) {
		Poruka poruka = porukaRepo.findById(id).orElseThrow();
		
		return porukaConverter.convertToDTO(poruka);
	}

	@Override
	public int create(PorukaDataDTO dto) {
		Poruka poruka = porukaConverter.convertToJPA(dto);
		
//		1. RTGS
//			1.1 Pronaci dnevna stanja banke duznika i poverioca
//			1.2 Povezati i azurirati dnevna stanja
//			1.3 Banci duznika poslati obavestenje o zaduzenju
//			1.4 Banci poverioca proslediti poruku i obavestenje o odobrenju
//		
//		2. KLIRING
//			2.1 Smestiti poruku na privremeno cuvanje do kliring ciklusa
//			2.2 Nakon kliring ciklusa razmotriti dalje korake
//			2.3 ...
//			2.4 ...
		
		return 0;
	}

	@Override
	public void update(int id, PorukaDataDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
