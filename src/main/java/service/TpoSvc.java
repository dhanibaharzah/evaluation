package service;

import java.util.List;

import dto.TPoDetailDto;
import dto.TPoDto;


public interface TpoSvc {
	
	public List<TPoDto> list();
	public List<TPoDetailDto> listDetail(String poNo);
	public List<TPoDto> find(String param);
	public TPoDto findOne();
	public void update(TPoDto dto);
	public void delete(TPoDto dto);
	public void save(TPoDto dto);

}

