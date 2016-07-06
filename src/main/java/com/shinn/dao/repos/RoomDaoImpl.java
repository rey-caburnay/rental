package com.shinn.dao.repos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.model.Room;

@Repository
public class RoomDaoImpl extends AbstractDaoImpl<Room> implements RoomDao{

    public RoomDaoImpl() throws Exception {
        super();
        setClazz(Room.class);
    }

    public Room getById(Integer id) throws Exception {
        return getObject("", id);
    }

    public List<Room> findAll() throws Exception {
        return getListResult("");
    }

    public void saveUpdate(Room model) throws Exception {
        // TODO Auto-generated method stub
        
    }

}
