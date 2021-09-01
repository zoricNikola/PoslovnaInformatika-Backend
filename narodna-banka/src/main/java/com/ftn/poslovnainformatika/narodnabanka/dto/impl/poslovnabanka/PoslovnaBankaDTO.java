package com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka;

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
public class PoslovnaBankaDTO implements PoslovnaBankaViewDTO {

    private int id;

    private String nazivBanke;

    private String swiftKod;

    private ObracunskiRacunViewDTO obracunskiRacun;

}
