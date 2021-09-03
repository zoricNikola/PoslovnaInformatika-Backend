package com.ftn.poslovnainformatika.narodnabanka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DnevnoStanjeDTO {

    private Integer id;

    private LocalDate datum;

    private Double prethodnoStanje;

    private Double prometNaTeret;

    private Double prometUKorist;

    private Double novoStanje;

    private ObracunskiRacunDTO obracunskiRacun;

}
