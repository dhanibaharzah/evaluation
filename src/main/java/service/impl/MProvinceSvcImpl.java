package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.MProvinceSvc;
import dao.MProvinceDao;
import entity.MProvince;
import entity.MProvincePK;

@Transactional
@Service("provSvc")
public class MProvinceSvcImpl implements MProvinceSvc {
	@Autowired
	private MProvinceDao dao;

	@Override
	public List<MProvince> list() {
		List<MProvince> lists = dao.findAll();
		return lists;
	}

	@Override
	public MProvince findOne(String provId) {
		MProvincePK pk = new MProvincePK();
		pk.setProvId(provId);
		MProvince list = dao.findOne(pk);
		return list;
		
		
	}
}
