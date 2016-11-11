package com.shinn.dao.repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.CollectionDetails;

@Repository
public class CollectionDetailsImpl extends AbstractDaoImpl<CollectionDetails> implements CollectionDetailsDao {

    public CollectionDetailsImpl() throws Exception {
        super();
       setClazz(CollectionDetails.class);
    }

    @Override
    public CollectionDetails getById(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CollectionDetails> findAll() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int saveUpdate(CollectionDetails model) throws Exception {
        String sqlStatement = "save-collection-details";
        if (model.getId() != null && model.getId() > 0) {
            sqlStatement = "update-collection-details";
        }
        return executeSaveUpate(sqlStatement, model);
    }

}
