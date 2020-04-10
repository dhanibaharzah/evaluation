package service;

import java.util.List;

import entity.MItem;

public interface MItemSvc {

	public List<MItem> list();
	public MItem findOne(String itemId);
}
