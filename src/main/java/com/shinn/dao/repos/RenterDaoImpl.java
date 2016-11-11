package com.shinn.dao.repos;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Transaction;

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
    public int saveUpdate(Renter model) throws Exception {
        //TODO update sql query to name parameters
        return executeSaveUpate("save-renter", model);
    }

    @Override
    public Renter getRenterByName(String lastname, String firstname, String initial)  {
        try {
            return getObject("get-renter-by-name", lastname, firstname, initial);
        }catch(Exception e) {
            return null;
        }
    }

	@Override
	public List<Renter> getRenters() {
	    List<Renter> renters = getListResult("get-active-renters");
		return renters;
	}

}
