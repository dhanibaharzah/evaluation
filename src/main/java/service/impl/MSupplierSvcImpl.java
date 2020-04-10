package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.MSupplierDao;
import entity.MSupplier;
import entity.MSupplierPK;
import service.MSupplierSvc;

@Transactional
@Service("supSvc")
public class MSupplierSvcImpl implements MSupplierSvc {
	
	@Autowired
	private MSupplierDao dao;
	
	@Override
	public List<MSupplier> list() {
		List<MSupplier> lists = dao.findAll();
		return lists;
	}

	@Override
	public MSupplier findOne(String supId) {
		MSupplierPK pk = new MSupplierPK();
		pk.setSupId(supId);
		MSupplier list = dao.findOne(pk);
		return list;
	}
	
}
