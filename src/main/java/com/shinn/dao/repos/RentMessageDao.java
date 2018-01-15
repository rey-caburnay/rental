package com.shinn.dao.repos;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.RentMessage;

public interface RentMessageDao extends GenericDao<RentMessage> {
  
  public RentMessage findByKey (String key); 

}
