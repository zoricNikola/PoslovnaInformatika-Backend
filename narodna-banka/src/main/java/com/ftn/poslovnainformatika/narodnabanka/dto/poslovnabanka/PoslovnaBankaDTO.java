package com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.ObracunskiRacunDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PoslovnaBankaDTO {

    private Integer sifraBanke;

    private String nazivBanke;

    private String swiftKod;

    private ObracunskiRacunDTO obracunskiRacun;

}
