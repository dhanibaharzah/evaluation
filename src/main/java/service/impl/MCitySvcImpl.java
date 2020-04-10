package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.MCityDao;
import entity.MCity;
import entity.MCityPK;
import service.MCitySvc;

@Transactional
@Service("citySvc")
public class MCitySvcImpl implements MCitySvc {
	
	@Autowired
	private MCityDao dao;
	
	@Override
	public List<MCity> list() {
		// TODO Auto-generated method stub
		List<MCity> lists = dao.findAll();
		return lists;
	}

	@Override
	public MCity findOne(String cityId) {
		MCityPK pk= new MCityPK();
		pk.setCityId(cityId);
		MCity list = dao.findOne(pk);
		return list;
	}

}
