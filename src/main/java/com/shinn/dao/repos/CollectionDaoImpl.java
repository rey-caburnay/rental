package com.shinn.dao.repos;

import java.util.List;
import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Collection;

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
    public void saveUpdate(Collection model) throws Exception {
       executeSaveUpate("", 
               model.getTxId(), 
               model.getRenterId(),
               model.getRoomId(),
               model.getAptId(),
               model.getAmountPaid(),
               model.getBalance(),
               model.getDeposit(),
               model.getTxDate(),
               model.getUserId(),
               model.getStatus());
    }

  

}
