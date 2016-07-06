package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.model.Room;

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

}
