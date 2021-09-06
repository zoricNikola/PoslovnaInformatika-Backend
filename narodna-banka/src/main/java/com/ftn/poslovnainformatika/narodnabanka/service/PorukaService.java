package com.ftn.poslovnainformatika.narodnabanka.service;

import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.common.BaseService;

import java.time.LocalDate;
import java.util.Set;

public interface PorukaService extends BaseService<PorukaDTO> {

    Set<PorukaDTO> getByPoslovnaBankaAndDatumRange(int bankaId, LocalDate startDatum, LocalDate endDatum);

}
