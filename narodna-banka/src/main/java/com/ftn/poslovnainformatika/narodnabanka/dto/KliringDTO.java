package com.ftn.poslovnainformatika.narodnabanka.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KliringDTO {
	
	private Integer id;
	
	private LocalDateTime vreme;

}
