package com.shinn.dao.repos;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Token;

@Repository
public class TokenDaoImpl extends AbstractDaoImpl<Token> implements TokenDao {

  public TokenDaoImpl() throws Exception {
    super();
    setClazz(Token.class);
  }

  @Override
  public Token getById(Integer id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Token> findAll() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int saveUpdate(Token model) throws Exception {
   return executeSaveUpate("token-save", model);
  }

  @Override
  public Token findBySeries(String series) {
    return getObject("token-findby-series", series);
  }

  @Override
  public int delete(String series) {
    try {
      return executeSaveUpate("toke-delete", series);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return 0;
    }
  }

}
