package com.ftn.poslovnainformatika.narodnabanka.converter.impl.poslovnabanka;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaDataDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;

@Component
public class PoslovnaBankaConverter implements DtoConverter<PoslovnaBanka, PoslovnaBankaViewDTO, PoslovnaBankaDataDTO> {

	@Override
	public PoslovnaBankaViewDTO convertToDTO(PoslovnaBanka source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PoslovnaBankaViewDTO> convertToDTO(Set<PoslovnaBanka> sources) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PoslovnaBanka convertToJPA(PoslovnaBankaDataDTO source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PoslovnaBanka> convertToJPA(Set<PoslovnaBankaDataDTO> sources) {
		// TODO Auto-generated method stub
		return null;
	}

}
