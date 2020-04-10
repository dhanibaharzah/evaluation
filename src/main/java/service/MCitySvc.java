package service;

import java.util.List;

import entity.MCity;

public interface MCitySvc {

	public List<MCity> list();
	public MCity findOne(String cityId);
}
