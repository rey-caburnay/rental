package com.shinn.dao.repos;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;

@Repository
public class RenterInfoDaoImpl extends AbstractDaoImpl<RenterInfo> implements RenterInfoDao {

    public RenterInfoDaoImpl() throws Exception {
        super();
        setClazz(RenterInfo.class);
    }

    @Override
    public List<RenterInfo> getRentersInfo(Integer renterId) {
        return getListResult("get-renters-info", renterId);
    }

}
