package com.shinn.dao.repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Apartment;
@Repository
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

    public int saveUpdate(Apartment model) throws Exception {
        return executeSaveUpate("save-apartment", model);
    }

 
}
