package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.ElectricProvider;

public interface ElectricProviderDao extends GenericDao<ElectricProvider> {
  
  public ElectricProvider getProvider(String provider);

}
