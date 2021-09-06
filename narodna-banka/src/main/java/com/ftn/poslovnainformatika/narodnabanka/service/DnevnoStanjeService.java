package com.ftn.poslovnainformatika.narodnabanka.service;

import java.time.LocalDate;
import java.util.Collection;

import com.ftn.poslovnainformatika.narodnabanka.dto.DnevnoStanjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.DnevnoStanje;
import com.ftn.poslovnainformatika.narodnabanka.service.common.BaseService;

public interface DnevnoStanjeService extends BaseService<DnevnoStanjeDTO> {
	
	DnevnoStanje getByBrojObracunskogRacunaAndDatum(String brojObracunskogRacuna, LocalDate datum);
	
	void saveAll(Collection<DnevnoStanje> stanja);
	
}
