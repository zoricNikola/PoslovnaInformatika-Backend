package com.ftn.poslovnainformatika.narodnabanka.converter;

import java.util.Set;

public interface DtoConverter<JPA, ViewDTO, DataDTO> {
	
	ViewDTO convertToDTO(JPA source);
	
	Set<ViewDTO> convertToDTO(Set<JPA> sources);
	
	JPA convertToJPA(DataDTO source);
	
	Set<JPA> convertToJPA(Set<DataDTO> sources);

}
