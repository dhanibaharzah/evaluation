package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import entity.TPoDetail;
import entity.TPoDetailPK;


public interface TPoDetailDao extends JpaRepository<TPoDetail, TPoDetailPK> {
//	@Query("select po, pod, s.supName, i.itemName, c.cityName, p.provName " 
//			+ "from TPo po, MSupplier s, MItem i, TPoDetail pod, MProvince p, MCity c, "
//			+ "where po.supId = s.supId "
//			+ "and po.poNo = pod.poNo "
//			+ "and po.cityId = c.cityId "
//			+ "and pod.itemId = i.itemId "
//			+ "and p.provId = c.provId ")
	@Query("select pod, i.itemName " 
			+ "from TPoDetail pod, MItem i "
			+ "where pod.itemId = i.itemId "
			+ "and pod.poNo =?")
	public List<Object[]> findByPo(String poNo);
	
	@Modifying
	@Query("delete from TPoDetail where poNo=?")
	public void deleteAll(String poNo);
}

//
//select po.*, pod.*, i.item_name, c.city_name, p.prov_name, s.sup_name from 
//t_po_detail pod, m_item i, t_po po, m_city c, m_province p, m_supplier s
//where po.po_no = pod.po_no
//and po.city_id = c.city_id
//and po.sup_id = s.sup_id
//and pod.item_id = i.item_id
//and p.prov_id = c.prov_id
