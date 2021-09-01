package com.ftn.poslovnainformatika.narodnabanka.dto.impl;

import com.ftn.poslovnainformatika.narodnabanka.dto.dnevnostanje.DnevnoStanjeViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.obracunskiracun.ObracunskiRacunViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poruka.PorukaViewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DnevnoStanjeDTO implements DnevnoStanjeViewDTO {

    private int id;

    private LocalDateTime datum;

    private double prethodnoStanje;

    private double prometNaTeret;

    private double prometUKorist;

    private double novoStanje;

    private ObracunskiRacunViewDTO obracunskiRacun;

    private Set<PorukaViewDTO> poruke;
}
