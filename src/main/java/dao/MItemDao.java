package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.MItem;
import entity.MItemPK;

public interface MItemDao extends JpaRepository<MItem, MItemPK> {

}
