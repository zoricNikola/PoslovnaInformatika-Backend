package com.ftn.poslovnainformatika.narodnabanka.converter.impl;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.ObavestenjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Obavestenje;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import com.ftn.poslovnainformatika.narodnabanka.repository.PorukaRepository;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.PoslovnaBankaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ObavestenjeConverter implements DtoConverter<Obavestenje, ObavestenjeDTO> {
    @Autowired
    private DtoConverter<Poruka, PorukaDTO> porukaConverter;

    @Autowired
    private  DtoConverter<PoslovnaBanka, PoslovnaBankaDTO> poslovnaBankaConverter;

    @Autowired
    private PorukaRepository porukaRepository;

    @Autowired
    PoslovnaBankaRepository poslovnaBankaRepository;

    @Override
    public ObavestenjeDTO convertToDTO(Obavestenje source) {
        PorukaDTO porukaDTO = porukaConverter.convertToDTO(source.getPoruka());
        PoslovnaBankaDTO poslovnaBankaDTO = poslovnaBankaConverter.convertToDTO(source.getPoslovnaBanka());

        ObavestenjeDTO dto = new ObavestenjeDTO(source.getId(), source.getVrstaObavestenja(), source.getSwiftKod(),
                source.getObracunskiRacun(), source.getDatumValute(), source.getSifraValute(), source.getIznos(),
                porukaDTO, poslovnaBankaDTO);

        return dto;
    }

    @Override
    public Set<ObavestenjeDTO> convertToDTO(Set<Obavestenje> sources) {
        Set<ObavestenjeDTO> result = new HashSet<>();
        for (Obavestenje jpa : sources) result.add(convertToDTO(jpa));

        return result;
    }

    @Override
    public Obavestenje convertToJPA(ObavestenjeDTO source) {
        Obavestenje jpa = new Obavestenje(null, source.getVrstaObavestenja(), source.getSwiftKod(), source.getObracunskiRacun(),
                source.getDatumValute(), source.getSifraValute(), source.getIznos(),
                porukaRepository.getById(source.getPoruka().getId()),
                poslovnaBankaRepository.getById(source.getPoslovnaBanka().getSifraBanke()));

        return jpa;
    }

    @Override
    public Set<Obavestenje> convertToJPA(Set<ObavestenjeDTO> sources) {
        Set<Obavestenje> result = new HashSet<>();
        for (ObavestenjeDTO dto : sources) result.add(convertToJPA(dto));

        return result;
    }
}
