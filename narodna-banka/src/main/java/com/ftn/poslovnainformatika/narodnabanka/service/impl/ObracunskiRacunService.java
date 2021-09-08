package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.DnevnoStanjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.IzvodObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.PorukaIzvodaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.StanjeObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.DnevnoStanje;
import com.ftn.poslovnainformatika.narodnabanka.service.PorukaService;
import com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.PoslovnaBankaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.ObracunskiRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.ObracunskiRacun;
import com.ftn.poslovnainformatika.narodnabanka.repository.ObracunskiRacunRepository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class ObracunskiRacunService implements com.ftn.poslovnainformatika.narodnabanka.service.ObracunskiRacunService {
	
	@Autowired
	private ObracunskiRacunRepository racunRepo;
	
	@Autowired
	private DtoConverter<ObracunskiRacun, ObracunskiRacunDTO> racunConverter;

	@Autowired
	private DtoConverter<DnevnoStanje, DnevnoStanjeDTO> dnevnoStanjeConverter;

	@Autowired
	private PoslovnaBankaService poslovnaBankaService;

	@Autowired
	private DnevnoStanjeService dnevnoStanjeService;

	@Autowired
	private PorukaService porukaService;

	@Autowired
	private WebClient webClient;

	@Resource(name = "poslovneBankeServices")
	public Map<Integer, String> poslovneBankeServices;


	@Override
	public Set<ObracunskiRacunDTO> getAll() {
		List<ObracunskiRacun> racuni = racunRepo.findAll();
		
		return racunConverter.convertToDTO(new HashSet<>(racuni));
	}
	
	@Override
	public ObracunskiRacun getReference(String brojRacuna) {
		return racunRepo.getById(brojRacuna);
	}

	@Override
	public Set<StanjeObracunskogRacunaDTO> getStanjaObracunskihRacuna(LocalDate datum) {
		Set<StanjeObracunskogRacunaDTO> stanja = new HashSet<>();
		Set<PoslovnaBankaDTO> banke = poslovnaBankaService.getAll();

		banke.forEach(poslovnaBankaDTO -> {
			DnevnoStanje dnevnoStanje = dnevnoStanjeService.
					getByBrojObracunskogRacunaAndDatum(poslovnaBankaDTO.getObracunskiRacun().getBrojObracunskogRacuna(), datum);

			if(dnevnoStanje != null) {
				StanjeObracunskogRacunaDTO stanje = new StanjeObracunskogRacunaDTO();
				stanje.setPoslovnaBanka(poslovnaBankaDTO);
				stanje.setDnevnoStanje(dnevnoStanjeConverter.convertToDTO(dnevnoStanje));
				stanja.add(stanje);
			}
		});
		return stanja;
	}

	@Override
	public IzvodObracunskogRacunaDTO getIzvodObracunskogRacuna(int bankaId, LocalDate startDatum, LocalDate endDatum) {
		Set<PorukaDTO> poruke = porukaService.getByPoslovnaBankaAndDatumRange(bankaId, startDatum, endDatum);
		IzvodObracunskogRacunaDTO izvod = getIzvod(poruke);

		return izvod;
	}

	//@Scheduled(cron = "0 10 1 * * ?")
	//@Scheduled(cron = "0 */1 * * * *")
	//@Scheduled(cron = "@monthly")
	public void sendIzvodObracunskogRacuna() {
		System.out.println("Slanje izvoda obracunskih racuna...");

		LocalDate today = LocalDate.now();
		LocalDate startPreviousMonth = today.minusMonths(1);
		LocalDate endPreviousMonth = startPreviousMonth.withDayOfMonth(startPreviousMonth.lengthOfMonth());

		System.out.println("Datumiiiii " + today + " " + startPreviousMonth + " " + endPreviousMonth);

		Set<PoslovnaBankaDTO> poslovneBanke = poslovnaBankaService.getAll();

		if(poslovneBanke != null && !poslovneBanke.isEmpty()){
			poslovneBanke.forEach(poslovnaBankaDTO -> {
				Set<PorukaDTO> poruke = porukaService.getByPoslovnaBankaAndDatumRange(poslovnaBankaDTO.getSifraBanke(), startPreviousMonth, endPreviousMonth);
				System.out.println("Exception " + porukaService.getByPoslovnaBankaAndDatumRange(poslovnaBankaDTO.getSifraBanke(), startPreviousMonth, endPreviousMonth));
				IzvodObracunskogRacunaDTO izvodPoslovneBanke = getIzvod(poruke);
				forwardIzvod(poslovnaBankaDTO.getSifraBanke(), izvodPoslovneBanke);
			});
		}
	}

	private IzvodObracunskogRacunaDTO getIzvod(Set<PorukaDTO> poruke) {
		IzvodObracunskogRacunaDTO izvod = new IzvodObracunskogRacunaDTO();
		izvod.setPorukeIzvoda(new HashSet<>());

		if(poruke != null && !poruke.isEmpty()){
			poruke.forEach(porukaDTO -> {
				PorukaIzvodaDTO porukaIzvodaDTO = new PorukaIzvodaDTO();
				porukaIzvodaDTO.setId(porukaDTO.getId());
				porukaIzvodaDTO.setDatum(porukaDTO.getDatum());
				porukaIzvodaDTO.setDatumValute(porukaDTO.getDatumValute());
				porukaIzvodaDTO.setSifraValute(porukaDTO.getSifraValute());
				porukaIzvodaDTO.setUkupanIznos(porukaDTO.getUkupanIznos());
				porukaIzvodaDTO.setVrstaPoruke(porukaDTO.getVrstaPoruke());
				porukaIzvodaDTO.setBankaDuznika(porukaDTO.getBankaDuznika());
				porukaIzvodaDTO.setBankaPoverioca(porukaDTO.getBankaPoverioca());
				izvod.getPorukeIzvoda().add(porukaIzvodaDTO);
			});
		}
		return izvod;
	}

	private void forwardIzvod(int bankaId, IzvodObracunskogRacunaDTO izvod) {
		webClient.post()
				.uri(String.format("%s%s", poslovneBankeServices.get(bankaId), "/receive/izvod/" + bankaId))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(izvod), IzvodObracunskogRacunaDTO.class)
				.exchangeToMono(response -> response.bodyToMono(Void.class))
				.subscribe(System.out::println);
	}

}
