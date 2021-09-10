package com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.TekuciRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.common.BaseService;

import java.util.Set;

public interface PoslovnaBankaService extends BaseService<PoslovnaBankaDTO> {

    Set<PoslovnaBankaDTO> getAll();
    
    Set<KlijentDTO> getPoslovnaBankaKlijenti(int sifraBanke);

	Set<TekuciRacunDTO> getPoslovnaBankaRacuni(int sifraBanke);
}
