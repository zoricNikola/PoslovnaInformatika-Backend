package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.ObavestenjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Obavestenje;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaObavestenja;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import com.ftn.poslovnainformatika.narodnabanka.repository.ObavestenjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

@Service
public class ObavestenjeService implements com.ftn.poslovnainformatika.narodnabanka.service.ObavestenjeService {
    @Autowired
    private DtoConverter<Obavestenje, ObavestenjeDTO> obavestenjeConverter;

    @Autowired
    private DtoConverter<Poruka, PorukaDTO> porukaConverter;

    @Autowired
    private ObavestenjeRepository obavestenjeRepository;

    @Autowired
    private WebClient webClient;
    
    @Resource(name = "poslovneBankeServices")
    public Map<Integer, String> poslovneBankeServices;

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

    public Set<ObavestenjeDTO> getAll(){
        List<Obavestenje> obavestenja = obavestenjeRepository.findAll();

        return obavestenjeConverter.convertToDTO((Set<Obavestenje>) obavestenja);
    }
    
    @Override
    public void sendObavestenja(Poruka poruka) {
    	Obavestenje obavestenjeDuznika = generateObavestenje(poruka, VrstaObavestenja.MT900);
    	
    	Mono<Void> monoD = webClient.post()
    			.uri(String.format("%s%s", poslovneBankeServices.get(obavestenjeDuznika.getPoslovnaBanka().getSifraBanke()), "/receive/notification"))
    			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    			.body(Mono.just(obavestenjeConverter.convertToDTO(obavestenjeDuznika)), ObavestenjeDTO.class)
    			.exchangeToMono(response -> response.bodyToMono(Void.class));
    	
    	monoD.subscribe(System.out::println);
    	
    	
    	Obavestenje obavestenjePoverioca = generateObavestenje(poruka, VrstaObavestenja.MT910);
    	
    	Mono<Void> monoP = webClient.post()
    			.uri(String.format("%s%s", poslovneBankeServices.get(obavestenjePoverioca.getPoslovnaBanka().getSifraBanke()), "/receive/notification"))
    			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    			.body(Mono.just(obavestenjeConverter.convertToDTO(obavestenjePoverioca)), ObavestenjeDTO.class)
    			.exchangeToMono(response -> response.bodyToMono(Void.class));
    	
    	monoP.subscribe(System.out::println);
    }

    private Obavestenje generateObavestenje(Poruka poruka, VrstaObavestenja vrsta) {
    	PoslovnaBanka banka = vrsta == VrstaObavestenja.MT900 ? poruka.getBankaDuznika() : poruka.getBankaPoverioca();
    	
    	Obavestenje obavestenje = new Obavestenje();
    	obavestenje.setVrstaObavestenja(vrsta);
    	obavestenje.setSwiftKod(banka.getSwiftKod());
    	obavestenje.setObracunskiRacun(banka.getObracunskiRacun().getBrojObracunskogRacuna());
    	obavestenje.setDatumValute(poruka.getDatumValute());
    	obavestenje.setSifraValute(poruka.getSifraValute());
    	obavestenje.setIznos(poruka.getUkupanIznos());
    	obavestenje.setPoruka(poruka);
    	obavestenje.setPoslovnaBanka(banka);
    	
    	obavestenje = obavestenjeRepository.save(obavestenje);
    	
    	return obavestenje;
    }

}
