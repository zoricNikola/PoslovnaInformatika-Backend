package com.ftn.poslovnainformatika.poslovnabanka.service.impl;

import com.ftn.poslovnainformatika.poslovnabanka.dto.NalogDTO;
import com.ftn.poslovnainformatika.poslovnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.poslovnabanka.dto.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.poslovnabanka.dto.VrstaPoruke;
import com.ftn.poslovnainformatika.poslovnabanka.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class PoslovnaBankaService implements com.ftn.poslovnainformatika.poslovnabanka.service.PoslovnaBankaService {

    private static final String NARODNA_BANKA_BASE_URL = "http://localhost:8080/api";
    private static final String FILE_PATH = "src/main/resources/data";

    private final WebClient webClient;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    public PoslovnaBankaService(){
        this.webClient = WebClient.builder()
                .baseUrl(NARODNA_BANKA_BASE_URL)
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

        return webClient.post()
                .uri("/poruke")
                .body(Mono.just(porukaDTO), PorukaDTO.class)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> sendClearing() {
        return null;
    }
}
