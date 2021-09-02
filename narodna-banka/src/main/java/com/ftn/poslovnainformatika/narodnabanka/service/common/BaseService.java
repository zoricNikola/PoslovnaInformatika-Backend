package com.ftn.poslovnainformatika.narodnabanka.service.common;

public interface BaseService<DTO> {
	
	public DTO getOne(int id);
	
	public int create(DTO dto);
	
	public void update(int id, DTO dto);
	
	public void delete(int id);

}
