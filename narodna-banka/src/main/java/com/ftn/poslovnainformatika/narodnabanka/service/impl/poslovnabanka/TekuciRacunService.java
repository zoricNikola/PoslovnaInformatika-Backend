package com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.TekuciRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.TekuciRacun;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.KlijentRepository;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.TekuciRacunRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

public class TekuciRacunService implements com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.TekuciRacunService {
    @Autowired
    private DtoConverter<TekuciRacun, TekuciRacunDTO> tekuciRacunConverter;

    @Autowired
    private TekuciRacunRepository tekuciRacunRepository;

    @Autowired
    private KlijentService klijentService;

    @Autowired
    private DtoConverter<Klijent, KlijentDTO> klijentConverter;

    @Autowired
    KlijentRepository klijentRepository;

    @Override
    public TekuciRacunDTO getOne(int id) {
        TekuciRacun tekuciRacun = tekuciRacunRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return tekuciRacunConverter.convertToDTO(tekuciRacun);
    }

    @Override
    public int create(TekuciRacunDTO tekuciRacunDTO) {
        TekuciRacun tekuciRacun;

        if (tekuciRacunDTO.getKlijent().getId() == null) {
            KlijentDTO klijentDTO = new KlijentDTO(null, tekuciRacunDTO.getKlijent().getIme(), tekuciRacunDTO.getKlijent().getPrezime(),
                    tekuciRacunDTO.getKlijent().getNaziv(), tekuciRacunDTO.getKlijent().getAdresa(),
                    tekuciRacunDTO.getKlijent().getPib(), tekuciRacunDTO.getKlijent().getMesto(),
                    tekuciRacunDTO.getKlijent().getDelatnost());


            int noviKlijentId = klijentService.create(klijentDTO);
            klijentDTO.setId(noviKlijentId);

            tekuciRacunDTO.setKlijent(klijentDTO);

            tekuciRacun = tekuciRacunConverter.convertToJPA(tekuciRacunDTO);
            tekuciRacun = tekuciRacunRepository.save(tekuciRacun);
        }else {
            tekuciRacun = tekuciRacunConverter.convertToJPA(tekuciRacunDTO);
            tekuciRacun = tekuciRacunRepository.save(tekuciRacun);
        }

        return Integer.valueOf(tekuciRacun.getBrojRacuna());
    }

    @Override
    public void update(int id, TekuciRacunDTO tekuciRacunDTO) {
        TekuciRacun tekuciRacun = tekuciRacunRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        if (!klijentRepository.findById(tekuciRacunDTO.getKlijent().getId()).isPresent()){
            KlijentDTO klijentDTO = new KlijentDTO(null, tekuciRacunDTO.getKlijent().getIme(), tekuciRacunDTO.getKlijent().getPrezime(),
                    tekuciRacunDTO.getKlijent().getNaziv(), tekuciRacunDTO.getKlijent().getAdresa(),
                    tekuciRacunDTO.getKlijent().getPib(), tekuciRacunDTO.getKlijent().getMesto(),
                    tekuciRacunDTO.getKlijent().getDelatnost());

            int noviKlijentId = klijentService.create(klijentDTO);
            klijentDTO.setId(noviKlijentId);

            tekuciRacunDTO.setKlijent(klijentDTO);

            tekuciRacun = tekuciRacunConverter.convertToJPA(tekuciRacunDTO);
            tekuciRacun = tekuciRacunRepository.save(tekuciRacun);
        }else {
            tekuciRacun.setKlijent(klijentConverter.convertToJPA(tekuciRacunDTO.getKlijent()));
            tekuciRacun = tekuciRacunConverter.convertToJPA(tekuciRacunDTO);
            tekuciRacun = tekuciRacunRepository.save(tekuciRacun);
        }
    }

    @Override
    public void delete(int id) {
        TekuciRacun tekuciRacun = tekuciRacunRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        tekuciRacunRepository.deleteById(id);
    }
}
