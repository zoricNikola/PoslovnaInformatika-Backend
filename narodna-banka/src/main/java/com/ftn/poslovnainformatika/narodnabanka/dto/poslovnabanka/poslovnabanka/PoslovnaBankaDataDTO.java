package com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.obracunskiracun.ObracunskiRacunViewDTO;

public interface PoslovnaBankaDataDTO {

    String getNazivBanke();
    void setNazivBanke(String naziv);

    String getSwiftKod();
    void setSwiftKod(String swiftKod);

    ObracunskiRacunViewDTO getObracunskiRacun();
    void setObracunskiRacun(ObracunskiRacunViewDTO obracunskiRacun);

}
