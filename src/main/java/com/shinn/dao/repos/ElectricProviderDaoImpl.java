package com.shinn.dao.repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.ElectricProvider;

@Repository
public class ElectricProviderDaoImpl extends AbstractDaoImpl<ElectricProvider> 
  implements ElectricProviderDao {

  public ElectricProviderDaoImpl() throws Exception {
    super();
    setClazz(ElectricProvider.class);
  }

  @Override
  public ElectricProvider getById(Integer id) throws Exception {
    return getById(id);
  }

  @Override
  public List<ElectricProvider> findAll() throws Exception {
    return findAll();
  }

  @Override
  public int saveUpdate(ElectricProvider model) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public ElectricProvider getProvider(String provider) {
    return getObject("electricprovider-by-provider", provider);
  }

}
