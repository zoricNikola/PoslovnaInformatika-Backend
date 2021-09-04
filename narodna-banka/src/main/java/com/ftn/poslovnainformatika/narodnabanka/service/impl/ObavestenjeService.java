package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.ObavestenjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Obavestenje;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.repository.ObavestenjeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

public class ObavestenjeService implements com.ftn.poslovnainformatika.narodnabanka.service.ObavestenjeService {
    @Autowired
    private DtoConverter<Obavestenje, ObavestenjeDTO> obavestenjeConverter;

    @Autowired
    private DtoConverter<Poruka, PorukaDTO> porukaConverter;

    @Autowired
    private ObavestenjeRepository obavestenjeRepository;

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
}
