package com.ftn.poslovnainformatika.narodnabanka.service.common;

public interface BaseService<ViewDTO, DataDTO> {
	
	public ViewDTO getOne(int id);
	
	public int create(DataDTO dto);
	
	public void update(int id, DataDTO dto);
	
	public void delete(int id);

}
