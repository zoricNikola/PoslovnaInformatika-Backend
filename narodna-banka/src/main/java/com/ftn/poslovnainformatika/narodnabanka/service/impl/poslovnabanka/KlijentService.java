package com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.DelatnostDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.MestoDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Delatnost;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Mesto;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.KlijentRepository;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.TekuciRacunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class KlijentService implements com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.KlijentService {
    @Autowired
    private DtoConverter<Klijent, KlijentDTO> klijentConverter;

    @Autowired
    private DtoConverter<Delatnost, DelatnostDTO> delatnostConverter;

    @Autowired
    private DtoConverter<Mesto, MestoDTO> mestoConverter;

    @Autowired
    private KlijentRepository klijentRepository;

    @Autowired
    private TekuciRacunRepository tekuciRacunRepository;

    @Override
    public KlijentDTO getOne(int id) {
        Klijent klijent = klijentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return klijentConverter.convertToDTO(klijent);
    }

    @Override
    public int create(KlijentDTO klijentDTO) {
        Klijent klijent = klijentConverter.convertToJPA(klijentDTO);

        klijent = klijentRepository.save(klijent);

        return klijent.getId();
    }

    @Override
    public void update(int id, KlijentDTO klijentDTO) {
        Klijent klijent = klijentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        klijent.setAdresa(klijentDTO.getAdresa());
        klijent.setDelatnost(delatnostConverter.convertToJPA(klijentDTO.getDelatnost()));
        klijent.setIme(klijentDTO.getIme());
        klijent.setMesto(mestoConverter.convertToJPA(klijentDTO.getMesto()));
        klijent.setPib(klijentDTO.getPib());
        klijent.setPrezime(klijentDTO.getPrezime());
        klijent.setNaziv(klijentDTO.getNaziv());
        klijent.setRacuni(tekuciRacunRepository.findByKlijent_Id(id, Pageable.unpaged()).toSet());

        klijentRepository.save(klijent);
    }

    @Override
    public void delete(int id) {
        Klijent klijent = klijentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        klijentRepository.deleteById(id);
    }

    public Set<KlijentDTO> getAll(){
        List<Klijent> klijenti = klijentRepository.findAll();

        return klijentConverter.convertToDTO((Set<Klijent>) klijenti);
    }
}
