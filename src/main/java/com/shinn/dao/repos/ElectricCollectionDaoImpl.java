package com.shinn.dao.repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.ElectricCollection;

@Repository
public class ElectricCollectionDaoImpl extends AbstractDaoImpl<ElectricCollection> implements ElectricCollectionDao {

  public ElectricCollectionDaoImpl() throws Exception {
    super();
    setClazz(ElectricCollection.class);
  }

  @Override
  public ElectricCollection getById(Integer id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ElectricCollection> findAll() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int saveUpdate(ElectricCollection model) throws Exception {
    String sqlStment = "save-electriccollection";
    if (model.getId() != null && model.getId() > 0) {
        sqlStment = "update-electriccollection";
    } 
    return executeSaveUpate(sqlStment, model);

  }

}
