package com.shinn.dao.repos;

import java.util.List;
import com.shinn.service.model.RenterInfo;

public interface RenterInfoDao {

    public List<RenterInfo> getRentersInfo(Integer renterId);
    /**
     * 
     * @param status
     * @return
     */
    public List<RenterInfo> getRentersInfo(String status);
    
    /**
     * get the list of tenant in a room
     * @param aptId
     * @param roomId
     * @return
     */
    public List<RenterInfo> getTenants(Integer aptId, Integer roomId); 
    
}
