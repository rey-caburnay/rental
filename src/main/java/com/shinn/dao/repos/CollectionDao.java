package com.shinn.dao.repos;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Collection;

public interface CollectionDao extends GenericDao<Collection> {
  
  Collection getLastPayment(Integer aptId, Integer roomId);
  
  Collection getLastPayment(Integer renterId, Integer aptId, Integer roomId);
}
