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

import java.util.HashSet;
import java.util.Set;

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

        TekuciRacunDTO dto = new TekuciRacunDTO(Integer.valueOf(source.getBrojRacuna()), poslovnaBankaDTO, klijentDTO);

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
        TekuciRacun jpa = new TekuciRacun(null, klijentRepository.getById(source.getKlijent().getId()),
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
