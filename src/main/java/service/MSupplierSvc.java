package service;

import java.util.List;

import entity.MSupplier;

public interface MSupplierSvc {

	public List<MSupplier> list();
	public MSupplier findOne(String supId);
}
