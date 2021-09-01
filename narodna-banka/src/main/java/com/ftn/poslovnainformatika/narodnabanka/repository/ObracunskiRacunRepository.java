package com.ftn.poslovnainformatika.narodnabanka.repository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.ObracunskiRacun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObracunskiRacunRepository extends JpaRepository<ObracunskiRacun, String> {

    Optional<ObracunskiRacun> findByPoslovnaBanka_SifraBanke(int sifraBanke);

}
