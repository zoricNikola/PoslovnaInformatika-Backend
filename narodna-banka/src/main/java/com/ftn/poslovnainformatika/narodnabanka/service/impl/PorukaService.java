package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.DnevnoStanjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.DnevnoStanje;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaPoruke;
import com.ftn.poslovnainformatika.narodnabanka.repository.PorukaRepository;

@Service
public class PorukaService implements com.ftn.poslovnainformatika.narodnabanka.service.PorukaService {

	@Autowired
	private PorukaRepository porukaRepo;
	
	@Autowired
	private DtoConverter<Poruka, PorukaDTO> porukaConverter;
	
	@Autowired
	private DnevnoStanjeService stanjeService;
	
	@Autowired
	private DtoConverter<DnevnoStanje, DnevnoStanjeDTO> stanjeConverter;
	
	@Override
	public PorukaDTO getOne(int id) {
		Poruka poruka = porukaRepo.findById(id).orElseThrow();
		
		return porukaConverter.convertToDTO(poruka);
	}

	@Override
	public int create(PorukaDTO dto) {
		Poruka poruka = porukaConverter.convertToJPA(dto);
		
		poruka = porukaRepo.save(poruka);
		
		if (poruka.getVrstaPoruke().equals(VrstaPoruke.MT103)) {
			handleRTGS(poruka);
		}
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
		
		return poruka.getId();
	}

	@Override
	public void update(int id, PorukaDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	private void handleRTGS(Poruka poruka) {
		LocalDate today = LocalDate.now();
		
		DnevnoStanje stanjeBankeDuznika = stanjeService.
				getByBrojObracunskogRacunaAndDatum(poruka.getBankaDuznika().getObracunskiRacun().getBrojObracunskogRacuna(), today);
		
		DnevnoStanje stanjeBankePoverioca = stanjeService.
				getByBrojObracunskogRacunaAndDatum(poruka.getBankaPoverioca().getObracunskiRacun().getBrojObracunskogRacuna(), today);
		
		stanjeBankeDuznika.setPrometNaTeret(stanjeBankeDuznika.getPrometNaTeret() + poruka.getUkupanIznos());
		stanjeBankeDuznika.setNovoStanje(stanjeBankeDuznika.getNovoStanje() - poruka.getUkupanIznos());
		poruka.setDnevnoStanjeBankeDuznika(stanjeBankeDuznika);
		
		stanjeBankePoverioca.setPrometUKorist(stanjeBankePoverioca.getPrometUKorist() + poruka.getUkupanIznos());
		stanjeBankePoverioca.setNovoStanje(stanjeBankePoverioca.getNovoStanje() + poruka.getUkupanIznos());
		poruka.setDnevnoStanjeBankePoverioca(stanjeBankePoverioca);
		
		try {
			porukaRepo.save(poruka);
			stanjeService.update(stanjeBankeDuznika.getId(), stanjeConverter.convertToDTO(stanjeBankeDuznika));
			stanjeService.update(stanjeBankePoverioca.getId(), stanjeConverter.convertToDTO(stanjeBankePoverioca));
			
//			Proslediti poruku i poslati obavestenja
		} catch (Exception e) {
//			rollback?
		}
		
	}

}
