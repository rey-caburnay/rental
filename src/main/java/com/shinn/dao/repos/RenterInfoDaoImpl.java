package com.shinn.dao.repos;

import java.util.List;
import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.RenterInfo;

public class RenterInfoDaoImpl extends AbstractDaoImpl<RenterInfo> implements RenterInfoDao {

    public RenterInfoDaoImpl() throws Exception {
        super();
    }

    @Override
    public List<RenterInfo> getRentersInfo(Integer renterId) {
        return getListResult("get-renters-info", renterId);
    }

}
