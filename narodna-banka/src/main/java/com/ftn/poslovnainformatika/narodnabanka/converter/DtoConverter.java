package com.ftn.poslovnainformatika.narodnabanka.converter;

import java.util.Set;

public interface DtoConverter<JPA, DTO> {
	
	DTO convertToDTO(JPA source);
	
	Set<DTO> convertToDTO(Set<JPA> sources);
	
	JPA convertToJPA(DTO source);
	
	Set<JPA> convertToJPA(Set<DTO> sources);

}
