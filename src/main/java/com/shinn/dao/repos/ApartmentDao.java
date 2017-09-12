package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Apartment;

public interface ApartmentDao extends GenericDao<Apartment>{
  
  public List<Apartment> getVacant();

}
