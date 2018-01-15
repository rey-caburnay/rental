package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.RentMessage;

public class RentMessageDaoImpl extends AbstractDaoImpl <RentMessage> implements RentMessageDao {

  public RentMessageDaoImpl() throws Exception {
    super();
    setClazz(RentMessage.class);
  }

  @Override
  public RentMessage getById(Integer id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<RentMessage> findAll() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int saveUpdate(RentMessage model) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public RentMessage findByKey(String key) {
    return getObject("message-findby-key", key);
  }

}
