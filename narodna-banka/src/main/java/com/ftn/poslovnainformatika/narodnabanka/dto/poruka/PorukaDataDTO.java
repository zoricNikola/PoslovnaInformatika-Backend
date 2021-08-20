package com.ftn.poslovnainformatika.narodnabanka.dto.poruka;

import java.time.LocalDate;
import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.nalog.NalogViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaPoruke;

public interface PorukaDataDTO {
	
	public LocalDate getDatum();
	public void setDatum(LocalDate datum);

	public VrstaPoruke getVrstaPoruke();
	public void setVrstaPoruke(VrstaPoruke vrstaPoruke);

	public double getUkupanIznos();
	public void setUkupanIznos(double ukupanIznos);

	public String getSifraValute();
	public void setSifraValute(String sifraValute);

	public LocalDate getDatumValute();
	public void setDatumValute(LocalDate datumValute);

	public PoslovnaBankaViewDTO getBankaDuznika();
	public void setBankaDuznika(PoslovnaBankaViewDTO bankaDuznika);

	public PoslovnaBankaViewDTO getBankaPoverioca();
	public void setBankaPoverioca(PoslovnaBankaViewDTO bankaPoverioca);
	
	public Set<NalogViewDTO> getNalozi();
	public void setNalozi(Set<NalogViewDTO> nalozi);

}
