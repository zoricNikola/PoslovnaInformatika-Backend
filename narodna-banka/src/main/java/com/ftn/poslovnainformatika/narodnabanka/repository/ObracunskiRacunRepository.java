package com.ftn.poslovnainformatika.narodnabanka.repository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.ObracunskiRacun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObracunskiRacunRepository extends JpaRepository<ObracunskiRacun, String> {

    Optional<ObracunskiRacun> findByPoslovnaBanka_SifraBanke(int sifraBanke);

    //prepraviti ovu metodu kada bude gotovo modelovanje entiteta Narodna banka
    Page<ObracunskiRacun> findByNarodnaBanka_Id(int bankaId, Pageable pageable);
}
