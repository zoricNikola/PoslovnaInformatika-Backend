package com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.TekuciRacun;


public interface TekuciRacunRepository extends JpaRepository<TekuciRacun, Integer> {
    Page<TekuciRacun> findByPoslovnaBanka_SifraBanke(int sifraPoslovneBanke, Pageable pageable);
    Page<TekuciRacun> findByKlijent_Id(int klijentId, Pageable pageable);
}
