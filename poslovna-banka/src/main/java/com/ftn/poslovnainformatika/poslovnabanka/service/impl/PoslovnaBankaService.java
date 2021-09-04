package com.ftn.poslovnainformatika.poslovnabanka.service.impl;

import com.ftn.poslovnainformatika.poslovnabanka.dto.*;
import com.ftn.poslovnainformatika.poslovnabanka.util.FileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class PoslovnaBankaService implements com.ftn.poslovnainformatika.poslovnabanka.service.PoslovnaBankaService {

    private static final String NARODNA_BANKA_BASE_URL = "http://localhost:8080/api";
    private static final String FILE_PATH = "src/main/resources/data";
    private static final Logger log = LoggerFactory.getLogger(PoslovnaBankaService.class);

    private final WebClient webClient;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    public PoslovnaBankaService(){
        this.webClient = WebClient.builder()
                .baseUrl(NARODNA_BANKA_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    @Override
    public Mono<Void> sendRTGS() {
        Set<NalogDTO> nalozi = fileUtil.getNalozi(FILE_PATH + "/rtgs.txt");

        PorukaDTO porukaDTO = createPorukaDTO(nalozi);
        porukaDTO.setVrstaPoruke(VrstaPoruke.MT103);

        Mono<Void> mono = webClient.post()
                .uri("/poruke")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(porukaDTO), PorukaDTO.class)
                .exchangeToMono(response -> response.bodyToMono(Void.class));

        mono.subscribe(System.out::println);

        return mono;
    }

    @Override
    public Mono<Void> sendClearing() {
        Set<NalogDTO> nalozi = fileUtil.getNalozi(FILE_PATH + "/clearing.txt");

        PorukaDTO porukaDTO = createPorukaDTO(nalozi);
        porukaDTO.setVrstaPoruke(VrstaPoruke.MT102);

        Mono<Void> mono = webClient.post()
                .uri("/poruke")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(porukaDTO), PorukaDTO.class)
                .exchangeToMono(response -> response.bodyToMono(Void.class));

        mono.subscribe(System.out::println);

        return mono;
    }

    @Override
    public void receivePorukaDTO(PorukaDTO dto) {
        if(dto.getVrstaPoruke() == VrstaPoruke.MT102) {
            log.info("=== PRIMLJEN CLEARING NALOG OD SERVISA NARODNE BANKE ===");
        }else if(dto.getVrstaPoruke() == VrstaPoruke.MT103) {
            log.info("=== PRIMLJEN RTGS NALOG OD SERVISA NARODNE BANKE ===");
        }
        dto.toString();
        log.info("=============================================================");
    }

    @Override
    public void receiveObavestenjeDTO(ObavestenjeDTO dto) {
        if(dto.getVrstaObavestenja() == VrstaObavestenja.MT900) {
            log.info("==== PRIMLJENA PORUKA O ZADUZENJU ===");
        }else if(dto.getVrstaObavestenja() == VrstaObavestenja.MT910) {
            log.info("==== PRIMLJENA PORUKA O ODOBRENJU ===");
        }
        dto.toString();
        log.info("==============================================================");
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

    private PorukaDTO createPorukaDTO(Set<NalogDTO> nalozi){
        NalogDTO nalogDTO = null;
        Optional<NalogDTO> nalog = nalozi.stream().findAny();
        if(nalog.isPresent()) { nalogDTO = nalog.get();}

        ArrayList<Double> listaIznosa = new ArrayList<>();
        nalozi.forEach(item -> listaIznosa.add(item.getIznos()));
        double ukupanIznosNaloga = listaIznosa.stream().mapToDouble(Double::doubleValue).sum();

        PorukaDTO porukaDTO = new PorukaDTO();
        porukaDTO.setDatum(LocalDate.now());
        porukaDTO.setSifraValute(nalogDTO.getSifraValute());
        porukaDTO.setUkupanIznos(ukupanIznosNaloga);
        porukaDTO.setDatumValute(LocalDate.now());
        porukaDTO.setNalozi(nalozi);
        Integer sifraBankeDuznika = Integer.parseInt(nalogDTO.getRacunDuznika().substring(0,3));
        Integer sifraBankePoverioca = Integer.parseInt(nalogDTO.getRacunPoverioca().substring(0,3));

        PoslovnaBankaDTO bankaDuznik = new PoslovnaBankaDTO();
        bankaDuznik.setSifraBanke(sifraBankeDuznika);

        PoslovnaBankaDTO bankaPoverioca = new PoslovnaBankaDTO();
        bankaPoverioca.setSifraBanke(sifraBankePoverioca);

        porukaDTO.setBankaDuznika(bankaDuznik);
        porukaDTO.setBankaPoverioca(bankaPoverioca);

        return porukaDTO;
    }

}
