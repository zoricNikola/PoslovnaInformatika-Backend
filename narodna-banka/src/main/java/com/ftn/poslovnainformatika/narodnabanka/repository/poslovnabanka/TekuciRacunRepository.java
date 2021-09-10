package com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.TekuciRacun;


public interface TekuciRacunRepository extends JpaRepository<TekuciRacun, String> {
    Set<TekuciRacun> findByPoslovnaBanka_SifraBanke(int sifraPoslovneBanke);
    Page<TekuciRacun> findByKlijent_Id(int klijentId, Pageable pageable);
}
