package com.shinn.dao.repos;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Token;

public interface TokenDao extends GenericDao<Token> {
  
  public Token findBySeries(String series);
  public int delete(String series);

}
