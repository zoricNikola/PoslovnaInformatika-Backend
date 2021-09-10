package com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.DelatnostDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Delatnost;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.DelatnostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DelatnostService implements com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.DelatnostService {
    @Autowired
    private DtoConverter<Delatnost, DelatnostDTO> delatnostConverter;

    @Autowired
    private DelatnostRepository delatnostRepository;

    @Override
    public DelatnostDTO getOne(int id) {
        Delatnost delatnost = delatnostRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return delatnostConverter.convertToDTO(delatnost);
    }

    @Override
    public int create(DelatnostDTO delatnostDTO) {
        Delatnost delatnost = delatnostConverter.convertToJPA(delatnostDTO);

        delatnost = delatnostRepository.save(delatnost);

        return delatnost.getId();
    }

    @Override
    public void update(int id, DelatnostDTO delatnostDTO) {
        Delatnost delatnost = delatnostRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        delatnost.setNazivDelatnosti(delatnostDTO.getNazivDelatnosti());
        delatnost.setSifraDelatnosti(delatnostDTO.getSifraDelatnosti());

        delatnostRepository.save(delatnost);
    }

    @Override
    public void delete(int id) {
        Delatnost delatnost = delatnostRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        delatnostRepository.deleteById(id);
    }

    public Set<DelatnostDTO> getAll(){
        List<Delatnost> delatnosti = delatnostRepository.findAll();

        Set<Delatnost> delatnostSet = new HashSet<>();
        for (Delatnost d : delatnosti) delatnostSet.add(d);

        return delatnostConverter.convertToDTO(delatnostSet);
    }
}
