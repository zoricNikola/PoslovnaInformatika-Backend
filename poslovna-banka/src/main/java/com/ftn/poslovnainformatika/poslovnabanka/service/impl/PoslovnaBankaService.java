package com.ftn.poslovnainformatika.poslovnabanka.service.impl;

import com.ftn.poslovnainformatika.poslovnabanka.dto.NalogDTO;
import com.ftn.poslovnainformatika.poslovnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.poslovnabanka.dto.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.poslovnabanka.dto.VrstaPoruke;
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
import java.util.HashSet;
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
        NalogDTO nalogDTO = fileUtil.getNalogRTGS(FILE_PATH + "/rtgs.txt");
        Set<NalogDTO> nalozi = new HashSet<>();
        nalozi.add(nalogDTO);

        PorukaDTO porukaDTO = new PorukaDTO();
        porukaDTO.setVrstaPoruke(VrstaPoruke.MT103);
        porukaDTO.setDatum(LocalDate.now());
        porukaDTO.setSifraValute(nalogDTO.getSifraValute());
        porukaDTO.setUkupanIznos(nalogDTO.getIznos());
        porukaDTO.setDatumValute(LocalDate.now());
        Integer sifraBankeDuznika = Integer.parseInt(nalogDTO.getRacunDuznika().substring(0,3));
        Integer sifraBankePoverioca = Integer.parseInt(nalogDTO.getRacunPoverioca().substring(0,3));

        PoslovnaBankaDTO bankaDuznik = new PoslovnaBankaDTO();
        bankaDuznik.setSifraBanke(sifraBankeDuznika);

        PoslovnaBankaDTO bankaPoverioca = new PoslovnaBankaDTO();
        bankaPoverioca.setSifraBanke(sifraBankePoverioca);

        porukaDTO.setBankaDuznika(bankaDuznik);
        porukaDTO.setBankaPoverioca(bankaPoverioca);

        porukaDTO.setNalozi(nalozi);

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
        return null;
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

}
