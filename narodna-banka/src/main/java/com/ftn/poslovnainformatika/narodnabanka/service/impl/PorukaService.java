package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.DnevnoStanje;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Kliring;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaPoruke;
import com.ftn.poslovnainformatika.narodnabanka.repository.KliringRepository;
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
	private KliringRepository kliringRepo;
	
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
			
			List<DnevnoStanje> stanja = new ArrayList<DnevnoStanje>();
			stanja.add(stanjeBankePoverioca);
			stanja.add(stanjeBankeDuznika);
			stanjeService.saveAll(stanja);
			
//			Proslediti poruku i poslati obavestenja
		} catch (Exception e) {
//			rollback?
		}
		
	}
	
//	@Scheduled(cron = "0 0 9-16 * * MON-FRI")
	@Scheduled(cron = "0 */5 * * * *")
	private void handleClearing() {
		System.out.println("Started clearing...");
		
		List<Poruka> poruke = porukaRepo.findByVrstaPorukeAndKliring(VrstaPoruke.MT102, null);
		
		Map<Integer, DnevnoStanje> stanja = new HashMap<>();
		
		Map<Integer, Double> prometNaTeret = new HashMap<>();
		Map<Integer, Double> prometUKorist = new HashMap<>();
		
		Kliring kliring = new Kliring(null, LocalDateTime.now(), new HashSet<Poruka>(poruke));
		
		LocalDate today = LocalDate.now();
		
		for (Poruka poruka : poruke) {
			int bankaDuznika = poruka.getBankaDuznika().getSifraBanke();
			
			if (!stanja.containsKey(bankaDuznika))
				stanja.put(bankaDuznika, stanjeService.getByBrojObracunskogRacunaAndDatum(poruka.getBankaDuznika()
						.getObracunskiRacun().getBrojObracunskogRacuna(), today));
			
			prometNaTeret.put(bankaDuznika, prometNaTeret.get(bankaDuznika) != null ? 
					prometNaTeret.get(bankaDuznika) : 0  + poruka.getUkupanIznos());
			
			
			int bankaPoverioca = poruka.getBankaPoverioca().getSifraBanke();
			
			if (!stanja.containsKey(bankaPoverioca))
				stanja.put(bankaPoverioca, stanjeService.getByBrojObracunskogRacunaAndDatum(poruka.getBankaPoverioca()
						.getObracunskiRacun().getBrojObracunskogRacuna(), today));
			
			prometUKorist.put(bankaPoverioca, prometUKorist.get(bankaPoverioca) != null ?
					prometUKorist.get(bankaPoverioca) : 0 + poruka.getUkupanIznos());
			
			poruka.setKliring(kliring);
			poruka.setDnevnoStanjeBankeDuznika(stanja.get(bankaDuznika));
			poruka.setDnevnoStanjeBankePoverioca(stanja.get(bankaPoverioca));
		}
		
		for (Integer sifraBanke : stanja.keySet()) {
			DnevnoStanje stanje = stanja.get(sifraBanke);
			
			Double naTeret = prometNaTeret.get(sifraBanke);
			Double uKorist = prometUKorist.get(sifraBanke);
			
			if (naTeret != null)
				stanje.setPrometNaTeret(stanje.getPrometNaTeret() + naTeret);
			
			if (uKorist != null)
				stanje.setPrometUKorist(stanje.getPrometUKorist() + uKorist);
			
			stanje.setNovoStanje(stanje.getPrethodnoStanje() - stanje.getPrometNaTeret() + stanje.getPrometUKorist());
		}
		
		try {
			stanjeService.saveAll(stanja.values());
			kliringRepo.save(kliring);
			porukaRepo.saveAll(poruke);
			
//			Proslediti poruke i poslati obavestenja
			
			System.out.println("...clearing done");
		} catch (Exception e) {
//			rollback?
		}
		
	}

}
