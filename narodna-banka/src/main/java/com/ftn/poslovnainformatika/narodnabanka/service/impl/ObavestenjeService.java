package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.ObavestenjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Obavestenje;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaObavestenja;
import com.ftn.poslovnainformatika.narodnabanka.repository.ObavestenjeRepository;
import com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka.PoslovnaBankaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;

public class ObavestenjeService implements com.ftn.poslovnainformatika.narodnabanka.service.ObavestenjeService {
    @Autowired
    private DtoConverter<Obavestenje, ObavestenjeDTO> obavestenjeConverter;

    @Autowired
    private DtoConverter<Poruka, PorukaDTO> porukaConverter;

    @Autowired
    private ObavestenjeRepository obavestenjeRepository;

    private final WebClient webClient;

    private static final String POSLOVNA_BANKA_PPOVERIOC_BASE_URL = "http://localhost:8081/api";
    private static final String POSLOVNA_BANKA_DUZNIK_BASE_URL = "http://localhost:8082/api";
    private static final Logger log = LoggerFactory.getLogger(PoslovnaBankaService.class);

    public ObavestenjeService() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    @Override
    public ObavestenjeDTO getOne(int id) {
        Obavestenje obavestenje = obavestenjeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return obavestenjeConverter.convertToDTO(obavestenje);
    }

    @Override
    public int create(ObavestenjeDTO obavestenjeDTO) {
        Obavestenje obavestenje = obavestenjeConverter.convertToJPA(obavestenjeDTO);

        obavestenje = obavestenjeRepository.save(obavestenje);

        return obavestenje.getId();
    }

    @Override
    public void update(int id, ObavestenjeDTO obavestenjeDTO) {
        Obavestenje obavestenje = obavestenjeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        obavestenje.setDatumValute(obavestenjeDTO.getDatumValute());
        obavestenje.setVrstaObavestenja(obavestenjeDTO.getVrstaObavestenja());
        obavestenje.setIznos(obavestenjeDTO.getIznos());
        obavestenje.setPoruka(porukaConverter.convertToJPA(obavestenjeDTO.getPoruka()));
        obavestenje.setObracunskiRacun(obavestenjeDTO.getObracunskiRacun());
        obavestenje.setSifraValute(obavestenjeDTO.getSifraValute());
        obavestenje.setSwiftKod(obavestenjeDTO.getSwiftKod());

        obavestenjeRepository.save(obavestenje);
    }

    @Override
    public void delete(int id) {
        Obavestenje obavestenje = obavestenjeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        obavestenjeRepository.deleteById(id);
    }

    public Mono<Void> sendObavestenjeDuznika(Poruka poruka){
        ObavestenjeDTO obavestenjeDTO = createObavestenjeDuznika(porukaConverter.convertToDTO(poruka));

        Mono<Void> mono = webClient.post()
                .uri(POSLOVNA_BANKA_DUZNIK_BASE_URL+"/receive/notification")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(obavestenjeDTO), ObavestenjeDTO.class)
                .exchangeToMono(response -> response.bodyToMono(Void.class));

        mono.subscribe(System.out::println);

        return mono;
    }

    public Mono<Void> sendObavestenjePoverioca(Poruka poruka){
        ObavestenjeDTO obavestenjeDTO = createObavestenjePoverica(porukaConverter.convertToDTO(poruka));

        Mono<Void> mono = webClient.post()
                .uri(POSLOVNA_BANKA_PPOVERIOC_BASE_URL+"/receive/notification")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(obavestenjeDTO), ObavestenjeDTO.class)
                .exchangeToMono(response -> response.bodyToMono(Void.class));

        mono.subscribe(System.out::println);

        return mono;
    }

    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response status: {}", clientResponse.statusCode());
            clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientResponse);
        });
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

    private ObavestenjeDTO createObavestenjeDuznika(PorukaDTO porukaDTO){
        ObavestenjeDTO obavestenjeDTO = new ObavestenjeDTO();

        obavestenjeDTO.setVrstaObavestenja(VrstaObavestenja.MT900);
        obavestenjeDTO.setSwiftKod(porukaDTO.getBankaDuznika().getSwiftKod());
        obavestenjeDTO.setObracunskiRacun(porukaDTO.getBankaDuznika().getObracunskiRacun().getBrojObracunskogRacuna());
        obavestenjeDTO.setDatumValute(porukaDTO.getDatumValute());
        obavestenjeDTO.setSifraValute(porukaDTO.getSifraValute());
        obavestenjeDTO.setIznos(porukaDTO.getUkupanIznos());
        obavestenjeDTO.setPoruka(porukaDTO);
        obavestenjeDTO.setPoslovnaBanka(porukaDTO.getBankaDuznika());

        return obavestenjeDTO;
    }

    private ObavestenjeDTO createObavestenjePoverica(PorukaDTO porukaDTO){
        ObavestenjeDTO obavestenjeDTO = new ObavestenjeDTO();

        obavestenjeDTO.setVrstaObavestenja(VrstaObavestenja.MT910);
        obavestenjeDTO.setSwiftKod(porukaDTO.getBankaPoverioca().getSwiftKod());
        obavestenjeDTO.setObracunskiRacun(porukaDTO.getBankaPoverioca().getObracunskiRacun().getBrojObracunskogRacuna());
        obavestenjeDTO.setDatumValute(porukaDTO.getDatumValute());
        obavestenjeDTO.setSifraValute(porukaDTO.getSifraValute());
        obavestenjeDTO.setIznos(porukaDTO.getUkupanIznos());
        obavestenjeDTO.setPoruka(porukaDTO);
        obavestenjeDTO.setPoslovnaBanka(porukaDTO.getBankaPoverioca());

        return obavestenjeDTO;
    }

}
