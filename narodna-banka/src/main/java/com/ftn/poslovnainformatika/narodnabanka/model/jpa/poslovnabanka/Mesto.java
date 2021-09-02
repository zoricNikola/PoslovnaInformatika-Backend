package com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mesto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mesto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mesto_id", unique = true, nullable = false)
    private Integer id;
	
	@Column(name = "postanski_broj", nullable = false)
    private Integer postanskiBroj;
	
	@Column(name = "naziv_mesta", nullable = false)
    private String nazivMesta;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mesto")
    private Set<Klijent> klijenti;
}
