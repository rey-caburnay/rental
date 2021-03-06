package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;

public interface RenterDao extends GenericDao<Renter>{

    /**
     *
     * @param lastname
     * @param firstname
     * @param initial
     * @return null if no result
     */
    public Renter getRenterByName(String lastname, String firstname, String initial);
    /**
     *
     * @return
     */
    public List<RenterInfo> getRenters();

}
