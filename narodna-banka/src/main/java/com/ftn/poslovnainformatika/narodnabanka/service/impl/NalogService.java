package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.NalogDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Nalog;
import com.ftn.poslovnainformatika.narodnabanka.repository.NalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NalogService implements com.ftn.poslovnainformatika.narodnabanka.service.NalogService {

    @Autowired
    private NalogRepository nalogRepo;

    @Autowired
    private DtoConverter<Nalog, NalogDTO> nalogConverter;

    @Override
    public Set<NalogDTO> getByPorukaId(int porukaId) {
        List<Nalog> naloziPoruke = nalogRepo.findByPoruka_Id(porukaId);

        return nalogConverter.convertToDTO(new HashSet<>(naloziPoruke));
    }
}
