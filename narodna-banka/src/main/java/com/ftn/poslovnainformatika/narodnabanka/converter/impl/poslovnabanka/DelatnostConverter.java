package com.ftn.poslovnainformatika.narodnabanka.converter.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.DelatnostDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Delatnost;

import java.util.HashSet;
import java.util.Set;

public class DelatnostConverter implements DtoConverter<Delatnost, DelatnostDTO> {
    @Override
    public DelatnostDTO convertToDTO(Delatnost source) {
        DelatnostDTO dto = new DelatnostDTO(source.getId(), source.getSifraDelatnosti(), source.getNazivDelatnosti());

        return dto;
    }

    @Override
    public Set<DelatnostDTO> convertToDTO(Set<Delatnost> sources) {
        Set<DelatnostDTO> result = new HashSet<>();
        for (Delatnost jpa : sources) result.add(convertToDTO(jpa));
        return result;
    }

    @Override
    public Delatnost convertToJPA(DelatnostDTO source) {
        Delatnost jpa = new Delatnost(null, source.getSifraDelatnosti(), source.getNazivDelatnosti(),null);

        return jpa;
    }

    @Override
    public Set<Delatnost> convertToJPA(Set<DelatnostDTO> sources) {
        Set<Delatnost> result = new HashSet<>();
        for (DelatnostDTO dto : sources) result.add(convertToJPA(dto));

        return result;
    }
}
