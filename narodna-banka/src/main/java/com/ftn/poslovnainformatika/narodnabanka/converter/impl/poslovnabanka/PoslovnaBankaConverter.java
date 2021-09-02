package com.ftn.poslovnainformatika.narodnabanka.converter.impl.poslovnabanka;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.obracunskiracun.ObracunskiRacunDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.obracunskiracun.ObracunskiRacunViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.ObracunskiRacun;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.PorukaObavestenja;
import com.ftn.poslovnainformatika.narodnabanka.repository.ObracunskiRacunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.TekuciRacun;

import javax.persistence.EntityNotFoundException;

@Component
public class PoslovnaBankaConverter implements DtoConverter<PoslovnaBanka, PoslovnaBankaViewDTO, PoslovnaBankaDataDTO> {

	@Autowired
	private DtoConverter<ObracunskiRacun, ObracunskiRacunViewDTO, ObracunskiRacunDataDTO> obracunskiRacunConverter;

	@Autowired
	private ObracunskiRacunRepository obracunskiRacunRepo;

	@Override
	public PoslovnaBankaViewDTO convertToDTO(PoslovnaBanka source) {
		return convertToPoslovnaBankaDTO(source);
	}

	@Override
	public Set<PoslovnaBankaViewDTO> convertToDTO(Set<PoslovnaBanka> sources) {
		Set<PoslovnaBankaViewDTO> result = new HashSet<>();

		if(sources != null && !sources.isEmpty()){
			sources.forEach(poslovnaBanka ->  result.add(convertToDTO(poslovnaBanka)));
		}
		return result;
	}

	@Override
	public PoslovnaBanka convertToJPA(PoslovnaBankaDataDTO source) {
		return convertToPoslovnaBankaJPA((PoslovnaBankaDTO) source);

	}

	@Override
	public Set<PoslovnaBanka> convertToJPA(Set<PoslovnaBankaDataDTO> sources) {
		Set<PoslovnaBanka> result = new HashSet<>();

		if(sources != null && !sources.isEmpty()){
			sources.forEach(poslovnaBankaDTO -> result.add(convertToJPA(poslovnaBankaDTO)));
		}
		return result;
	}

	private PoslovnaBankaDTO convertToPoslovnaBankaDTO(PoslovnaBanka source) {
		if(source == null) throw new NullPointerException();

		PoslovnaBankaDTO poslovnaBankaDTO = new PoslovnaBankaDTO(source.getSifraBanke(), source.getNazivBanke(),
				source.getSwiftKod(), obracunskiRacunConverter.convertToDTO(source.getObracunskiRacun()));

		return poslovnaBankaDTO;
	}

	private PoslovnaBanka convertToPoslovnaBankaJPA(PoslovnaBankaDTO source) {
		if(source == null || source.getObracunskiRacun() == null) throw new NullPointerException();

		PoslovnaBanka poslovnaBanka = new PoslovnaBanka();
		poslovnaBanka.setNazivBanke(source.getNazivBanke());
		poslovnaBanka.setSwiftKod(source.getSwiftKod());
		poslovnaBanka.setPorukeBankeDuznika(new HashSet<>());
		poslovnaBanka.setPorukeBankePoverioca(new HashSet<>());
		poslovnaBanka.setPorukeObavestenja(new HashSet<>());

		ObracunskiRacun obracunskiRacun = new ObracunskiRacun();
		obracunskiRacun.setBrojObracunskogRacuna(source.getObracunskiRacun().getBrojObracunskogRacuna());
		obracunskiRacun.setDnevnaStanja(new HashSet<>());
		obracunskiRacun.setPoslovnaBanka(poslovnaBanka);
		poslovnaBanka.setObracunskiRacun(obracunskiRacun);

		return poslovnaBanka;
	}

}
