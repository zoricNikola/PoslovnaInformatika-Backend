package com.ftn.poslovnainformatika.narodnabanka.dto.impl;

import com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.racun.RacunViewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RacunDTO implements RacunViewDTO {
    private int id;
    private PoslovnaBankaDTO poslovnaBanka;
    private KlijentDTO klijent;
}
