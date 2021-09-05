package com.ftn.poslovnainformatika.narodnabanka.converter.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.KliringDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Kliring;

@Component
public class KliringConverter implements DtoConverter<Kliring, KliringDTO> {

	@Override
	public KliringDTO convertToDTO(Kliring source) {
		if (source == null) return null;
		
		KliringDTO dto = new KliringDTO(source.getId(), source.getVreme());
		
		return dto;
	}

	@Override
	public Set<KliringDTO> convertToDTO(Set<Kliring> sources) {
		Set<KliringDTO> result = new HashSet<>();
		for (Kliring jpa : sources) result.add(convertToDTO(jpa));
		return result;
	}

	@Override
	public Kliring convertToJPA(KliringDTO source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Kliring> convertToJPA(Set<KliringDTO> sources) {
		// TODO Auto-generated method stub
		return null;
	}

}
