package com.ftn.poslovnainformatika.narodnabanka.dto.impl;

import com.ftn.poslovnainformatika.narodnabanka.dto.obracunskiracun.ObracunskiRacunViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaViewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObracunskiRacunDTO implements ObracunskiRacunViewDTO {

    private String brojObracunskogRacuna;

    private PoslovnaBankaViewDTO poslovnaBanka;

}
