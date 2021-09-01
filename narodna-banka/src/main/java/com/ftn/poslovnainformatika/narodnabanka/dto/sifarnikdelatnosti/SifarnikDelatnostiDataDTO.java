package com.ftn.poslovnainformatika.narodnabanka.dto.sifarnikdelatnosti;

import com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka.KlijentDTO;

import java.util.Set;

public interface SifarnikDelatnostiDataDTO {
    public double getSifraDelatnosti();
    public void setSifraDelatnosti(double sifraDelatnosti);

    public Set<KlijentDTO> getKlijetni();
    public void setKlijenti(Set<KlijentDTO> klijenti);
}
