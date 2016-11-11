package com.shinn.dao.repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Sms;

@Repository
public class SmsDaoImpl extends AbstractDaoImpl<Sms> implements SmsDao {

    public SmsDaoImpl() throws Exception {
        super();
        setClazz(Sms.class);
    }

    @Override
    public Sms getById(Integer id) throws Exception {
        return getObject("get-sms-by-id", id);
    }

    @Override
    public List<Sms> findAll() throws Exception {
        return getListResult("get-all-sms");
    }

    @Override
    public int saveUpdate(Sms model) throws Exception {
        String sqlStment = "save-sms";
        if (model.getId() != null && model.getId() > 0) {
            sqlStment = "update-sms";
        }
        //TODO update sql query to name parameters
        return executeSaveUpate(sqlStment, model);
    }
}
