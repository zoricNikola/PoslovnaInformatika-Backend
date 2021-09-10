package com.ftn.poslovnainformatika.narodnabanka.converter.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.DelatnostDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.MestoDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Delatnost;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Mesto;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.DelatnostRepository;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.MestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class KlijentConverter implements DtoConverter<Klijent, KlijentDTO> {

    @Autowired
    private DtoConverter<Mesto, MestoDTO> mestoConverter;

    @Autowired
    private DtoConverter<Delatnost, DelatnostDTO> delatnostConverter;

    @Autowired
    private MestoRepository mestoRepo;

    @Autowired
    private DelatnostRepository delatnostRepo;

    @Override
    public KlijentDTO convertToDTO(Klijent source) {
        return convertToKlijentDTO(source);
    }

    @Override
    public Set<KlijentDTO> convertToDTO(Set<Klijent> sources) {
        Set<KlijentDTO> result = new HashSet<>();

        if(sources != null && !sources.isEmpty()){
            sources.forEach(klijent -> result.add(convertToDTO(klijent)));
        }
        return result;
    }

    @Override
    public Klijent convertToJPA(KlijentDTO source) {
        return convertToKlijentJPA(source);
    }

    @Override
    public Set<Klijent> convertToJPA(Set<KlijentDTO> sources) {
        Set<Klijent> result = new HashSet<>();

        if(sources != null && !sources.isEmpty()){
            sources.forEach(klijentDTO -> result.add(convertToJPA(klijentDTO)));
        }
        return result;
    }

    private KlijentDTO convertToKlijentDTO(Klijent source) {
        if(source == null || source.getDelatnost() == null || source.getMesto() == null) throw new NullPointerException();

        MestoDTO mestoDTO = mestoConverter.convertToDTO(source.getMesto());
        DelatnostDTO delatnostDTO = delatnostConverter.convertToDTO(source.getDelatnost());

        KlijentDTO klijentDTO = new KlijentDTO(source.getId(), source.getIme(), source.getPrezime(), source.getNaziv(),
                source.getAdresa(), source.getPib(), mestoDTO, delatnostDTO);

        return klijentDTO;
    }

    private Klijent convertToKlijentJPA(KlijentDTO source) {
        if(source == null || source.getMesto() == null) throw new NullPointerException();

        Optional<Mesto> mesto = mestoRepo.findById(source.getMesto().getId());
        Optional<Delatnost> delatnost = delatnostRepo.findById(source.getDelatnost().getId());
        
        if(mesto.isEmpty()) throw new EntityNotFoundException();

        Klijent klijent = new Klijent(source.getId(), source.getIme(), source.getPrezime(), source.getNaziv(),
                source.getAdresa(), source.getPib(), delatnost.get(), mesto.get(), new HashSet<>());

        return klijent;
    }
}
