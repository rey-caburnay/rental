package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Room;

public interface RoomDao extends GenericDao<Room> {
    /**
     * 
     * @param aptId
     * @return
     * @throws Exception
     */
    public List<Room> getRooms(Integer aptId) throws Exception;
    
    /**
     * 
     * @param aptId
     * @return
     * @throws Exception
     */
    public List<Room> getVacantRoom(Integer aptId) throws Exception;
    /**
     * 
     * @param aptId
     * @return
     * @throws Exception
     */
    public List<Room> getRoomsReport(Integer aptId) throws Exception;
    
    public Room getRoom(Integer aptId, Integer roomId);
    
}
