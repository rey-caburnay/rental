package com.shinn.dao.repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Authority;

@Repository
public class AuthorityDaoImpl extends AbstractDaoImpl<Authority> implements AuthorityDao {

  public AuthorityDaoImpl() throws Exception {
    super();
    setClazz(Authority.class);
  }

  @Override
  public Authority getById(Integer id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Authority> findAll() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int saveUpdate(Authority model) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<Authority> findByUserId(Integer id) {
    return getListResult("authority-findby-userid", id);
  }

}
