package com.ftn.poslovnainformatika.poslovnabanka.service;

import reactor.core.publisher.Mono;

public interface PoslovnaBankaService {

    Mono<Void> sendRTGS();

    Mono<Void> sendClearing();
}
