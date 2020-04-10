package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.MCity;
import entity.MCityPK;

public interface MCityDao extends JpaRepository<MCity, MCityPK> {

}
