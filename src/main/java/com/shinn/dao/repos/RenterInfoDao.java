package com.shinn.dao.repos;

import java.util.List;
import com.shinn.service.model.RenterInfo;

public interface RenterInfoDao {

    public List<RenterInfo> getRentersInfo(Integer renterId);
}