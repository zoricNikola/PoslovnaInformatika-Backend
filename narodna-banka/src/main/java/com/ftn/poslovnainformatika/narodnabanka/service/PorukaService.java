package com.ftn.poslovnainformatika.narodnabanka.service;

import com.ftn.poslovnainformatika.narodnabanka.dto.NalogDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.PorukaIzvodaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.service.common.BaseService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PorukaService extends BaseService<PorukaDTO> {

    Set<PorukaDTO> getByDnevnoStanjeId(int stanjeId);
    
    Set<NalogDTO> getNaloziPoruke(int porukaId);

	List<PorukaIzvodaDTO> getPorukeIzvoda(int sifraBanke, LocalDate startDatum, LocalDate endDatum);
	
	void doRTGS(Poruka poruka);
	
	void doClearing();

}
