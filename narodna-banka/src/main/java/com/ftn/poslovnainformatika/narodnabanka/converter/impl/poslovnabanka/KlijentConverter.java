package com.ftn.poslovnainformatika.narodnabanka.converter.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.klijent.KlijentDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.klijent.KlijentViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;

import java.util.HashSet;
import java.util.Set;

public class KlijentConverter implements DtoConverter<Klijent, KlijentViewDTO, KlijentDataDTO> {

    @Override
    public KlijentViewDTO convertToDTO(Klijent source) {
        return convertToKlijentDTO(source);
    }

    @Override
    public Set<KlijentViewDTO> convertToDTO(Set<Klijent> sources) {
        Set<KlijentViewDTO> result = new HashSet<>();

        if(sources != null && !sources.isEmpty()){
            sources.forEach(klijent -> result.add(convertToDTO(klijent)));
        }
        return result;
    }

    @Override
    public Klijent convertToJPA(KlijentDataDTO source) {
        return convertToKlijentJPA((KlijentDTO) source);
    }

    @Override
    public Set<Klijent> convertToJPA(Set<KlijentDataDTO> sources) {
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
