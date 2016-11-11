package com.shinn.dao.repos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Room;

@Repository
public class RoomDaoImpl extends AbstractDaoImpl<Room> implements RoomDao{

    public RoomDaoImpl() throws Exception {
        super();
        setClazz(Room.class);
    }

    public Room getById(Integer id) throws Exception {
        return getObject("get-room-by-id", id);
    }

    public List<Room> findAll() throws Exception {
        return getListResult("");
    }

    public int saveUpdate(Room model) throws Exception {
        String sqlStment = "save-room";
        if (model.getId() != null && model.getId() > 0) {
            sqlStment = "update-room";
        }
        return executeSaveUpate(sqlStment, model);
    }

    @Override
    public List<Room> getRooms(Integer aptId) {
        return getListResult("get-rooms", aptId);
    }
}
