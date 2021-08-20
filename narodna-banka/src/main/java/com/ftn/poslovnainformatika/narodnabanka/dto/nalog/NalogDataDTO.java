package com.ftn.poslovnainformatika.narodnabanka.dto.nalog;

import java.time.LocalDate;

public interface NalogDataDTO {
	
	public String getDuznik();
	public void setDuznik(String duznik);

	public String getPoverilac();
	public void setPoverilac(String poverilac);

	public String getRacunDuznika();
	public void setRacunDuznika(String racunDuznika);

	public String getRacunPoverioca();
	public void setRacunPoverioca(String racunPoverioca);

	public String getSvrhaPlacanja();
	public void setSvrhaPlacanja(String svrhaPlacanja);

	public double getIznos();
	public void setIznos(double iznos);

	public String getSifraValute();
	public void setSifraValute(String sifraValute);

	public LocalDate getDatum();
	public void setDatum(LocalDate datum);

	public boolean isHitno();
	public void setHitno(boolean hitno);

	public String getPozivNaBrojZaduzenja();
	public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja);

	public String getPozivNaBrojOdobrenja();
	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja);

	public Integer getModelZaduzenja();
	public void setModelZaduzenja(Integer modelZaduzenja);

	public Integer getModelOdobrenja();
	public void setModelOdobrenja(Integer modelOdobrenja);

}
