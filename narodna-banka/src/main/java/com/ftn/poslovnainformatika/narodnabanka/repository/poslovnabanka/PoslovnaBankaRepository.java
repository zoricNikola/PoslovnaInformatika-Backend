package com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;

import java.util.Optional;

public interface PoslovnaBankaRepository extends JpaRepository<PoslovnaBanka, Integer> {

    Optional<PoslovnaBanka> findBySwiftKod(String swiftKod);

    Optional<PoslovnaBanka> findByObracunskiRacun_BrojObracunskogRacuna(String brojObracunskogRacuna);

}
