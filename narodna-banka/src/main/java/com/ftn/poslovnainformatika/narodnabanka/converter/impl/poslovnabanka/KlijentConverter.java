package com.ftn.poslovnainformatika.narodnabanka.converter.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class KlijentConverter implements DtoConverter<Klijent, KlijentDTO> {

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
        return convertToKlijentJPA((KlijentDTO) source);
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
        if(source == null) throw new NullPointerException();

        KlijentDTO klijentDTO = new KlijentDTO(source.getId(), source.getIme(), source.getPrezime(), source.getNaziv(),
                source.getAdresa(), source.getPib(), null, null);

        return klijentDTO;
    }

    private Klijent convertToKlijentJPA(KlijentDTO source) {
        if(source == null) throw new NullPointerException();

        Klijent klijent = new Klijent(source.getId(), source.getIme(), source.getPrezime(), source.getNaziv(),
                source.getAdresa(), source.getPib(), null, null, new HashSet<>());

        return klijent;
    }
}
