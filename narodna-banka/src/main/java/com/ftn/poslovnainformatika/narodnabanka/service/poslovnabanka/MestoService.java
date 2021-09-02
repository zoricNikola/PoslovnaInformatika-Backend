package com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka;

import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.MestoDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.common.BaseService;

public interface MestoService extends BaseService<MestoDTO> {
	
	Set<MestoDTO> getAll();

}
