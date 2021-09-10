package com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.TekuciRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.TekuciRacun;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.KlijentRepository;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.TekuciRacunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TekuciRacunService{
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


    public TekuciRacunDTO getOne(String id) {
        TekuciRacun tekuciRacun = tekuciRacunRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return tekuciRacunConverter.convertToDTO(tekuciRacun);
    }


    public int create(TekuciRacunDTO tekuciRacunDTO) {
        return 0;
    }


    public String createe(TekuciRacunDTO tekuciRacunDTO) {
        TekuciRacun tekuciRacun;

        if (tekuciRacunDTO.getKlijent().getId() == null) {
            KlijentDTO klijentDTO = new KlijentDTO(null, tekuciRacunDTO.getKlijent().getIme(), tekuciRacunDTO.getKlijent().getPrezime(),
                    tekuciRacunDTO.getKlijent().getNaziv(), tekuciRacunDTO.getKlijent().getAdresa(),
                    tekuciRacunDTO.getKlijent().getPib(), tekuciRacunDTO.getKlijent().getMesto(),
                    tekuciRacunDTO.getKlijent().getDelatnost());


            tekuciRacunDTO.setKlijent(klijentDTO);

            tekuciRacun = tekuciRacunConverter.convertToJPA(tekuciRacunDTO);
            tekuciRacun = tekuciRacunRepository.save(tekuciRacun);
        }else {
            tekuciRacun = tekuciRacunConverter.convertToJPA(tekuciRacunDTO);
            tekuciRacun.setBrojRacuna(tekuciRacunDTO.getId().toString());
            tekuciRacun = tekuciRacunRepository.save(tekuciRacun);
        }

        return tekuciRacun.getBrojRacuna();
    }

    public void delete(String id) {
        TekuciRacun tekuciRacun = tekuciRacunRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        tekuciRacunRepository.deleteById(id);
    }

    public Set<TekuciRacunDTO> getAll(){
        List<TekuciRacun> tekuciRacuni = tekuciRacunRepository.findAll();

        Set<TekuciRacun> tekuciRacuniSet = new HashSet<>();
        for (TekuciRacun tr : tekuciRacuni) tekuciRacuniSet.add(tr);

        return tekuciRacunConverter.convertToDTO(tekuciRacuniSet);
    }
}
