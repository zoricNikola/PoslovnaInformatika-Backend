package com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka;

import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.common.BaseService;

public interface KlijentService extends BaseService<KlijentDTO> {
	
	Set<KlijentDTO> getKlijentiByBanka(int sifraBanke);
}
