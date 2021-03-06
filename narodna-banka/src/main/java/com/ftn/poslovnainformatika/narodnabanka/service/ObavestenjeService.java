package com.ftn.poslovnainformatika.narodnabanka.service;

import com.ftn.poslovnainformatika.narodnabanka.dto.ObavestenjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Poruka;
import com.ftn.poslovnainformatika.narodnabanka.service.common.BaseService;

public interface ObavestenjeService extends BaseService<ObavestenjeDTO> {

	void sendObavestenja(Poruka poruka);
}
