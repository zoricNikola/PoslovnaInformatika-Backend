package com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.ObracunskiRacunDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PoslovnaBankaDTO {

    private Integer sifraBanke;

    private String nazivBanke;

    private String swiftKod;

    private ObracunskiRacunDTO obracunskiRacun;

    @Override
    public String toString() {
        return nazivBanke;
    }
}
