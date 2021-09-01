package com.ftn.poslovnainformatika.narodnabanka.dto.porukaobavestenja;

import com.ftn.poslovnainformatika.narodnabanka.dto.impl.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaPoruke;

import java.time.LocalDate;

public interface PorukaObavestenjaDataDTO {
    public VrstaPoruke getVrstaPoruke();
    public void setVrstaPoruke(VrstaPoruke vrstaPoruke);

    public String getSwiftKod();
    public void setSwiftKod(String swiftKod);

    public String getObracunskiRacun();
    public void setObracunskiRacun(String obracunskiRacun);

    public LocalDate getDatumValute();
    public void setDatumValute(LocalDate datumValute);

    public String getSifraValute();
    public void setSifraValute(String sifraValute);

    public double getIznos();
    public void setIznos(double iznos);

    public PorukaDTO getPoruka();
    public void setPoruka(PorukaDTO poruka);

    public PoslovnaBankaDTO getPoslovnaBanka();
    public void setPoslovnaBanka(PoslovnaBankaDTO poslovnaBanka);
}
