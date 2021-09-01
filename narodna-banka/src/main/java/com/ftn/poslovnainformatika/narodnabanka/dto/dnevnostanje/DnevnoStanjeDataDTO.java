package com.ftn.poslovnainformatika.narodnabanka.dto.dnevnostanje;

import com.ftn.poslovnainformatika.narodnabanka.dto.obracunskiracun.ObracunskiRacunViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poruka.PorukaViewDTO;

import java.time.LocalDateTime;
import java.util.Set;

public interface DnevnoStanjeDataDTO {

    LocalDateTime getDatum();
    void setDatum(LocalDateTime datum);

    double getPrethodnoStanje();
    void setPrethodnoStanje(double stanje);

    double getPrometNaTeret();
    void setPrometNaTeret(double prometNaTeret);

    double getPrometUKorist();
    void setPrometUKorist(double prometUKorist);

    double getNovoStanje();
    void setNovoStanje(double stanje);

    ObracunskiRacunViewDTO getObracunskiRacun();
    void setObracunskiRacun(ObracunskiRacunViewDTO obracunskiRacun);

    Set<PorukaViewDTO> getPoruke();
    void setPoruke(Set<PorukaViewDTO> poruke);
}
