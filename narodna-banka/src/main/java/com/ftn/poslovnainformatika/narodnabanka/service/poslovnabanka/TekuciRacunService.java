package com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka;

import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.TekuciRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.common.BaseService;

public interface TekuciRacunService extends BaseService<TekuciRacunDTO> {
	
	Set<TekuciRacunDTO> getRacuniByBanka(int sifraBanke);

	TekuciRacunDTO getOne(String id);

	String createe(TekuciRacunDTO tekuciRacunDTO);

	void delete(String id);
}
