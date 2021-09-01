package com.ftn.poslovnainformatika.narodnabanka.converter.impl;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.dnevnostanje.DnevnoStanjeDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.dnevnostanje.DnevnoStanjeViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.impl.DnevnoStanjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.obracunskiracun.ObracunskiRacunDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.obracunskiracun.ObracunskiRacunViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.DnevnoStanje;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.ObracunskiRacun;
import com.ftn.poslovnainformatika.narodnabanka.repository.ObracunskiRacunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DnevnoStanjeConverter implements DtoConverter<DnevnoStanje, DnevnoStanjeViewDTO, DnevnoStanjeDataDTO> {

    @Autowired
    private DtoConverter<ObracunskiRacun, ObracunskiRacunViewDTO, ObracunskiRacunDataDTO> obracunskiRacunConverter;

    @Autowired
    private ObracunskiRacunRepository obracunskiRacunRepo;

    @Override
    public DnevnoStanjeViewDTO convertToDTO(DnevnoStanje source) {
        return convertToDnevnoStanjeDTO(source);
    }

    @Override
    public Set<DnevnoStanjeViewDTO> convertToDTO(Set<DnevnoStanje> sources) {
        Set<DnevnoStanjeViewDTO> result = new HashSet<>();

        if(sources != null && !sources.isEmpty()){
            sources.forEach(dnevnoStanje -> result.add(convertToDTO(dnevnoStanje)));
        }
        return result;
    }

    @Override
    public DnevnoStanje convertToJPA(DnevnoStanjeDataDTO source) {
        return convertToDnevnoStanjeJPA((DnevnoStanjeDTO) source);
    }

    @Override
    public Set<DnevnoStanje> convertToJPA(Set<DnevnoStanjeDataDTO> sources) {
        Set<DnevnoStanje> result = new HashSet<>();

        if(sources != null && !sources.isEmpty()){
            sources.forEach(dnevnoStanjeDTO -> result.add(convertToJPA(dnevnoStanjeDTO)));
        }
        return result;
    }

    private DnevnoStanjeDTO convertToDnevnoStanjeDTO(DnevnoStanje source) {
        if(source == null) throw new EntityNotFoundException();

        DnevnoStanjeDTO dnevnoStanjeDTO = new DnevnoStanjeDTO(source.getId(), source.getDatum(), source.getPrethodnoStanje(),
                source.getPrometNaTeret(), source.getPrometUKorist(), source.getNovoStanje(),
                obracunskiRacunConverter.convertToDTO(source.getObracunskiRacun()), null);

        return dnevnoStanjeDTO;
    }

    private DnevnoStanje convertToDnevnoStanjeJPA(DnevnoStanjeDTO source) {
        if(source == null || source.getObracunskiRacun() == null) throw new NullPointerException();

        Optional<ObracunskiRacun> obracunskiRacun = obracunskiRacunRepo.findById(source.getObracunskiRacun().getBrojObracunskogRacuna());
        if(obracunskiRacun.isEmpty()) throw new EntityNotFoundException();

        DnevnoStanje dnevnoStanje = new DnevnoStanje(source.getId(), source.getDatum(), source.getPrethodnoStanje(),
                source.getPrometNaTeret(), source.getPrometUKorist(), source.getNovoStanje(), new HashSet<>(), obracunskiRacun.get());

        return dnevnoStanje;
    }
}
