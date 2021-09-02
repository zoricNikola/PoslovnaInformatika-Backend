package com.ftn.poslovnainformatika.narodnabanka.converter.impl.poslovnabanka;

import java.util.HashSet;
import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.MestoDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Mesto;

public class MestoConverter implements DtoConverter<Mesto, MestoDTO> {

	@Override
	public MestoDTO convertToDTO(Mesto source) {
		if (source == null) throw new IllegalArgumentException();
		
		MestoDTO dto = new MestoDTO(source.getId(), source.getPostanskiBroj(), source.getNazivMesta());
		
		return dto;
	}

	@Override
	public Set<MestoDTO> convertToDTO(Set<Mesto> sources) {
		if (sources == null || sources.isEmpty()) throw new IllegalArgumentException();
		
		Set<MestoDTO> result = new HashSet<>();
		sources.forEach(mesto -> result.add(convertToDTO(mesto)));
		
		return result;
	}

	@Override
	public Mesto convertToJPA(MestoDTO source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Mesto> convertToJPA(Set<MestoDTO> sources) {
		// TODO Auto-generated method stub
		return null;
	}

}
