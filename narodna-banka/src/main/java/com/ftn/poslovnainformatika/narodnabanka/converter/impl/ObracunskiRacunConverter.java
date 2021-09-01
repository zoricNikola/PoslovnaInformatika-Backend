package com.ftn.poslovnainformatika.narodnabanka.converter.impl;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.impl.ObracunskiRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.obracunskiracun.ObracunskiRacunDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.obracunskiracun.ObracunskiRacunViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.ObracunskiRacun;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.PoslovnaBankaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ObracunskiRacunConverter implements DtoConverter<ObracunskiRacun, ObracunskiRacunViewDTO, ObracunskiRacunDataDTO> {

    @Autowired
    private DtoConverter<PoslovnaBanka, PoslovnaBankaViewDTO, PoslovnaBankaDataDTO> poslovnaBankaConverter;

    @Autowired
    private PoslovnaBankaRepository poslovnaBankaRepo;

    @Override
    public ObracunskiRacunViewDTO convertToDTO(ObracunskiRacun source) {
        return convertToObracunskiRacunDTO(source);
    }

    @Override
    public Set<ObracunskiRacunViewDTO> convertToDTO(Set<ObracunskiRacun> sources) {
        Set<ObracunskiRacunViewDTO> result = new HashSet<>();

        if(sources != null && !sources.isEmpty()){
            sources.forEach(obracunskiRacun -> result.add(convertToDTO(obracunskiRacun)));
        }
        return result;
    }

    @Override
    public ObracunskiRacun convertToJPA(ObracunskiRacunDataDTO source) {
        return convertToObracunskiRacunJPA((ObracunskiRacunDTO) source);
    }

    @Override
    public Set<ObracunskiRacun> convertToJPA(Set<ObracunskiRacunDataDTO> sources) {
        Set<ObracunskiRacun> result = new HashSet<>();

        if(sources != null && !sources.isEmpty()){
            sources.forEach(obracunskiRacunDTO -> result.add(convertToJPA(obracunskiRacunDTO)));
        }
        return result;
    }

    private ObracunskiRacunDTO convertToObracunskiRacunDTO(ObracunskiRacun source) {
        if(source == null) throw new NullPointerException();

        ObracunskiRacunDTO obracunskiRacunDTO = new ObracunskiRacunDTO(source.getBrojObracunskogRacuna(),
                poslovnaBankaConverter.convertToDTO(source.getPoslovnaBanka()));

        return obracunskiRacunDTO;
    }


    private ObracunskiRacun convertToObracunskiRacunJPA(ObracunskiRacunDTO source) {
        if(source == null) throw new NullPointerException();

        Optional<PoslovnaBanka> poslovnaBanka = poslovnaBankaRepo.findByObracunskiRacun_BrojObracunskogRacuna(source.getBrojObracunskogRacuna());
        if(poslovnaBanka.isEmpty()) throw new EntityNotFoundException();

        ObracunskiRacun obracunskiRacun = new ObracunskiRacun(source.getBrojObracunskogRacuna(),
                poslovnaBanka.get(), new HashSet<>());

        return obracunskiRacun;
    }
}
