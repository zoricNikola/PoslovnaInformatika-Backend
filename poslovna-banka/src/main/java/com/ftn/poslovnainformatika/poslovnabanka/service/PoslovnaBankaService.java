package com.ftn.poslovnainformatika.poslovnabanka.service;

import com.ftn.poslovnainformatika.poslovnabanka.dto.IzvodObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.poslovnabanka.dto.ObavestenjeDTO;
import com.ftn.poslovnainformatika.poslovnabanka.dto.PorukaDTO;
import reactor.core.publisher.Mono;

public interface PoslovnaBankaService {

    Mono<Void> sendRTGS();

    Mono<Void> sendClearing();

    void receivePorukaDTO(PorukaDTO dto);

    void receiveObavestenjeDTO(ObavestenjeDTO dto);

    void receiveIzvodObracunskogRacunaDTO(int bankaId, IzvodObracunskogRacunaDTO izvodDTO);
}
