package com.ftn.poslovnainformatika.narodnabanka.dto.racun;

import com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka.PoslovnaBankaDTO;

public interface RacunDataDTO {
    public PoslovnaBankaDTO getPoslovnaBanka();
    public void setPoslovnaBanka(PoslovnaBankaDTO poslovnaBanka);

    public KlijentDTO getKlijent();
    public void setKlijent(KlijentDTO klijent);
}
