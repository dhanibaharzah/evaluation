package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.MCityDao;
import dao.MItemDao;
import dao.MProvinceDao;
import dao.MSupplierDao;
import dao.TPoDao;
import dao.TPoDetailDao;
import dto.TPoDetailDto;
import dto.TPoDto;
import entity.MCity;
import entity.MCityPK;
import entity.MProvince;
import entity.MProvincePK;
import entity.MSupplier;
import entity.MSupplierPK;
import entity.TPo;
import entity.TPoDetail;
import entity.TPoPK;
import service.TpoSvc;

@Transactional
@Service("orderSvc")
public class TpoSvcImpl implements TpoSvc {
	
	@Autowired
	private MCityDao daoCity;
	
	@Autowired
	private MItemDao daoItem;
	
	@Autowired
	private MProvinceDao daoProv;
	
	@Autowired
	private MSupplierDao daoSup;
	
	@Autowired
	private TPoDetailDao daoDetail;
	
	@Autowired
	private TPoDao dao;
	
	@Override
	public List<TPoDto> list() {
		List<TPoDto> lists = new ArrayList<TPoDto>();
		List<TPo> list = dao.findAll();
		for(TPo o : list){
			TPoDto dto = new TPoDto();
			MSupplierPK pk = new MSupplierPK();
			pk.setSupId(o.getSupId());
			MSupplier s = daoSup.findOne(pk);
			
			MCityPK pkC = new MCityPK();
			pkC.setCityId(o.getCityId());
			MCity c = daoCity.findOne(pkC);
			
			MProvincePK pkP = new MProvincePK();
			pkP.setProvId(c.getProvId());
			MProvince p = daoProv.findOne(pkP);
			
			dto.setPoNo(o.getPoNo());
			dto.setSupId(o.getSupId());
			dto.setPoShipment(o.getPoShipment());
			dto.setPoNotes(o.getPoNotes());
			dto.setDiscount(o.getDiscount());
			dto.setCityId(o.getCityId());
			dto.setPoAddress(o.getPoAddress());
			dto.setSupAddress(s.getSupAddress());
			dto.setPoDate(o.getPoDate());
			dto.setPoExpDate(o.getPoExpDate());
			dto.setProvName(p.getProvName());
			dto.setProvId(p.getProvId());
			dto.setTotal(o.getTotal());
			dto.setSupName(s.getSupName());
			dto.setCityName(c.getCityName());
			List<Object[]> details = daoDetail.findByPo(o.getPoNo());
			List<TPoDetailDto> detail = new ArrayList<>();
			for(Object[] d: details){
				TPoDetailDto det = new TPoDetailDto();
				TPoDetail tod = (TPoDetail) d[0];
				det.setPoNo(tod.getPoNo());
				det.setItemId(tod.getItemId());
				det.setItemPrice(tod.getItemPrice());
				det.setItemQty(tod.getItemQty());
				det.setSubtotal(tod.getSubtotal());
				det.setItemName((String) d[1]);
				detail.add(det);
			}
			dto.setDetails(detail);
			lists.add(dto);
		}
		return lists;
	}
	

	@Override
	public List<TPoDetailDto> listDetail(String poNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TPoDto> find(String param) {
		List<Object[]> list = dao.findAllOrderBySearch(param);
		List<TPoDto> dtos = new ArrayList<>();
		
		for (Object[] obj: list){
			TPoDto dto = new TPoDto();
			
//			get dan set data
			TPo order = (TPo) obj[0];
			dto.setPoNo(order.getPoNo());
			dto.setPoDate(order.getPoDate());
			dto.setTotal(order.getTotal());
			dto.setSupName((String)obj[1]);
			
//			tambah data
			dtos.add(dto);
			
		}	
		
		return dtos;
	}

	@Override
	public TPoDto findOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(TPoDto dto) {
		daoDetail.deleteAll(dto.getPoNo());
		for(TPoDetailDto o : dto.getDetails()){
			TPoDetail det = new TPoDetail();
			det.setPoNo(dto.getPoNo());
			det.setItemId(o.getItemId());
			det.setItemPrice(o.getItemPrice());
			det.setItemQty(o.getItemQty());
			det.setSubtotal(o.getSubtotal());
			daoDetail.save(det);
		}
		TPo po = new TPo();
		po.setPoNo(dto.getPoNo());
		po.setSupId(dto.getSupId());
		po.setCityId(dto.getCityId());
		po.setTotal(dto.getTotal());
		po.setDiscount(dto.getDiscount());
		po.setPoAddress(dto.getPoAddress());
		po.setPoDate(dto.getPoDate());
		po.setPoExpDate(dto.getPoExpDate());
		po.setPoNotes(dto.getPoNotes());
		po.setPoShipment(dto.getPoShipment());
		
		dao.save(po);
	}

	@Override
	public void delete(TPoDto dto) {
		daoDetail.deleteAll(dto.getPoNo());
		TPoPK pk = new TPoPK();
		pk.setPoNo(dto.getPoNo());
		dao.delete(pk);
	}

	@Override
	public void save(TPoDto dto) {
		for(TPoDetailDto o : dto.getDetails()){
			TPoDetail det = new TPoDetail();
			det.setPoNo(dto.getPoNo());
			det.setItemId(o.getItemId());
			det.setItemPrice(o.getItemPrice());
			det.setItemQty(o.getItemQty());
			det.setSubtotal(o.getSubtotal());
			daoDetail.save(det);
		}
		TPo po = new TPo();
		po.setPoNo(dto.getPoNo());
		po.setSupId(dto.getSupId());
		po.setTotal(dto.getTotal());
		
		dao.save(po);
	}

}
