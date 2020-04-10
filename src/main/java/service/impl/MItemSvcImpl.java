package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.MItemDao;
import entity.MItem;
import entity.MItemPK;
import service.MItemSvc;

@Transactional
@Service("itemSvc")
public class MItemSvcImpl implements MItemSvc {
	
	@Autowired
	private MItemDao dao;
	
	@Override
	public List<MItem> list() {
		List<MItem> lists = dao.findAll();
		return lists;
	}

	@Override
	public MItem findOne(String itemId) {
		MItemPK pk= new MItemPK();
		pk.setItemId(itemId);
		MItem list = dao.findOne(pk);
		return list;
	}

}
