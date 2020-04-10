package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.TPo;
import entity.TPoPK;

public interface TPoDao extends JpaRepository<TPo, TPoPK> {
//	@Query("select po, s.supName " + "from TPo po, MSupplier s "
//			+ "where po.supId = s.supId ")
//	public List<Object[]> findAllOrder();
//	
	@Query("select po, s.supName " + "from TPo po, MSupplier s "
			+ "where po.supId = s.supId "
			+ "and (po.poNo like %:cari% or s.supName like %:cari%) ")
	public List<Object[]> findAllOrderBySearch(@Param("cari") String cari);
}

// SELECT po.*, s.sup_name FROM dbo.t_po po, dbo.m_supplier s where po.sup_id = s.sup_id