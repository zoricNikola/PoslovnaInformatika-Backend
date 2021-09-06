package com.ftn.poslovnainformatika.narodnabanka.service;

import com.ftn.poslovnainformatika.narodnabanka.dto.NalogDTO;

import java.util.Set;

public interface NalogService {

    Set<NalogDTO> getByPorukaId(int porukaId);
}
