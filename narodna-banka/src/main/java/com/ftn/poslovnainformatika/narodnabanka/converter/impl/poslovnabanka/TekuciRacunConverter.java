package com.ftn.poslovnainformatika.narodnabanka.converter.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.TekuciRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.TekuciRacun;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.KlijentRepository;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.PoslovnaBankaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TekuciRacunConverter implements DtoConverter<TekuciRacun, TekuciRacunDTO> {
    @Autowired
    private DtoConverter<PoslovnaBanka, PoslovnaBankaDTO> poslovnaBankaConverter;

    @Autowired
    private DtoConverter<Klijent, KlijentDTO> klijentConverter;

    @Autowired
    private KlijentRepository klijentRepository;

    @Autowired
    private PoslovnaBankaRepository poslovnaBankaRepository;

    @Override
    public TekuciRacunDTO convertToDTO(TekuciRacun source) {
        PoslovnaBankaDTO poslovnaBankaDTO = poslovnaBankaConverter.convertToDTO(source.getPoslovnaBanka());
        KlijentDTO klijentDTO = klijentConverter.convertToDTO(source.getKlijent());

        TekuciRacunDTO dto = new TekuciRacunDTO(source.getBrojRacuna(), poslovnaBankaDTO, klijentDTO);

        return dto;
    }

    @Override
    public Set<TekuciRacunDTO> convertToDTO(Set<TekuciRacun> sources) {
        Set<TekuciRacunDTO> result = new HashSet<>();
        for(TekuciRacun jpa : sources) result.add(convertToDTO(jpa));

        return result;
    }

    @Override
    public TekuciRacun convertToJPA(TekuciRacunDTO source) {
    	Klijent klijent;
    	
    	if (source.getKlijent().getId() == null) 
    		klijent = klijentConverter.convertToJPA(source.getKlijent());
    	else
    		klijent = klijentRepository.getById(source.getKlijent().getId());
    	
        TekuciRacun jpa = new TekuciRacun(source.getBrojRacuna(), klijent,
                                            poslovnaBankaRepository.getById(source.getPoslovnaBanka().getSifraBanke()));

        return jpa;
    }

    @Override
    public Set<TekuciRacun> convertToJPA(Set<TekuciRacunDTO> sources) {
        Set<TekuciRacun> result = new HashSet<>();
        for (TekuciRacunDTO dto : sources) result.add(convertToJPA(dto));

        return result;
    }
}
