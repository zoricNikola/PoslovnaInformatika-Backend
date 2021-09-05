package com.ftn.poslovnainformatika.narodnabanka.model.jpa;

import java.time.LocalDateTime;
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
@Table(name = "kliring")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Kliring {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kliring_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "vreme", nullable = false)
	private LocalDateTime vreme;
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "kliring")
	private Set<Poruka> obradjenePoruke;

}
