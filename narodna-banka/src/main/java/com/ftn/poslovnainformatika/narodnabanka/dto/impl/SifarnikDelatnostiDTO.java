package com.ftn.poslovnainformatika.narodnabanka.dto.impl;

import com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.sifarnikdelatnosti.SifarnikDelatnostiViewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SifarnikDelatnostiDTO implements SifarnikDelatnostiViewDTO {
    private int id;
    private double sifraDelatnosti;
    private String nazivDelatnosti;
    private Set<KlijentDTO> klijenti;
}
