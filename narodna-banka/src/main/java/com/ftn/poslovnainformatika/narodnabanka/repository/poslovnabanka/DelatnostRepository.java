package com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Delatnost;

public interface DelatnostRepository extends JpaRepository<Delatnost, Integer> {
}
