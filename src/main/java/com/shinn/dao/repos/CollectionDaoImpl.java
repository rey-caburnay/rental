package com.shinn.dao.repos;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Collection;
@Repository
public class CollectionDaoImpl extends AbstractDaoImpl<Collection> implements CollectionDao {

    public CollectionDaoImpl() throws Exception {
        super();
        setClazz(Collection.class);
    }

    @Override
    public Collection getById(Integer id) throws Exception {
       return getObject("", id);
    }

    @Override
    public List<Collection> findAll() throws Exception {
        return getListResult("");
    }

    @Override
    public int saveUpdate(Collection model) throws Exception {
       return executeSaveUpate("save-collection", model);
    }

    @Override
    public Collection getLastPayment(Integer aptId, Integer roomId) {
      return getObject("collection-lastpayment", aptId, roomId);
    }
    
    @Override
    public Collection getLastPayment(Integer renterId, Integer aptId, Integer roomId) {
      return getObject("collection-by-renter-lastpayment", renterId, aptId, roomId);
    }

    @Override
    public Collection getByBillingNo(String billingNo) {
      return getObject("collection-findby-billing", billingNo);
    }
}
