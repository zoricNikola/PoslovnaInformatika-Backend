package com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.ObracunskiRacunDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoslovnaBankaDTO {

    private Integer sifraBanke;

    private String nazivBanke;

    private String swiftKod;

    private ObracunskiRacunDTO obracunskiRacun;

}
