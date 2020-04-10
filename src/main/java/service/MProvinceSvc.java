package service;

import java.util.List;

import entity.MProvince;

public interface MProvinceSvc {

	public List<MProvince> list();
	public MProvince findOne(String provId);
}
