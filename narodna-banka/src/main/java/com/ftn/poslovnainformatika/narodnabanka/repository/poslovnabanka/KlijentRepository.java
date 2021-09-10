package com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;

public interface KlijentRepository extends JpaRepository<Klijent, Integer> {
	
	Set<Klijent> findByRacuni_PoslovnaBanka_SifraBanke(int sifraBanke);

}
