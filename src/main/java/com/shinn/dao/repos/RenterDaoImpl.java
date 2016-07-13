package com.shinn.dao.repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Renter;

@Repository
public class RenterDaoImpl extends AbstractDaoImpl<Renter> implements RenterDao {

    public RenterDaoImpl() throws Exception {
        super();
        setClazz(Renter.class);
    }

    @Override
    public Renter getById(Integer id) throws Exception {
        return getObject("get-renter-by-id", id);
    }

    @Override
    public List<Renter> findAll() throws Exception {
        return getListResult("");
    }

    @Override
    public void saveUpdate(Renter model) throws Exception {
        executeSaveUpate("save-renter",
            model.getFirstName(),
            model.getLastName(),
            model.getInitial(),
            model.getEmail(),
            model.getMobileNo(),
            model.getTelno(),
            model.getAddress(),
            model.getIdPresented(),
            model.getEmergencyContact(),
            model.getStatus()
            );
        
    }

    @Override
    public Renter getRenterByName(String lastname, String firstname, String initial)  {
        try {
            return getObject("get-renter-by-name", lastname, firstname, initial);
        }catch(Exception e) {
            return null;
        }
    }

}
