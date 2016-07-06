package com.shinn.dao.repos;

import java.sql.ResultSet;
import java.util.List;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.model.Apartment;

public class ApartmentDaoImpl extends AbstractDaoImpl<Apartment> implements ApartmentDao {

	public ApartmentDaoImpl() throws Exception {
		super();
		setClazz(Apartment.class);
	}

	public Apartment getById(Integer id) throws Exception {
		return getObject("get-apartment", id);
	}

	public List<Apartment> findAll() throws Exception {
		return getListResult("get-apartments");
	}

    public void saveUpdate(Apartment model) throws Exception {
        executeSaveUpate("save-apartment",
                model.getName(),
                model.getPersonInCharge(),
                model.getAptType(),
                model.getMobileNo(),
                model.getTelNo(),
                model.getAddress1(),
                model.getAddress2(),
                model.getStatus());
    }

 
}
