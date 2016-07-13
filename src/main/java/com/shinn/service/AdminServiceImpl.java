package com.shinn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.ApartmentDao;
import com.shinn.dao.repos.RoomDao;
import com.shinn.service.model.Apartment;
import com.shinn.service.model.Expense;
import com.shinn.ui.model.Response;
import com.shinn.service.model.Room;

/**
 * administrative service for the system
 * @author shinn
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ApartmentDao apartmentDao;
    
    @Autowired
    RoomDao roomDao;

    public Response<Apartment> createApartment(Apartment apt) throws Exception {

        return null;
    }

    public Response<Apartment> getApartment(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    

    /* (non-Javadoc)
     * @see com.shinn.service.AdminService#getApartments()
     */
    @Override
    public Response<Apartment> getApartments() {
      Response<Apartment> resp = new Response<Apartment>();
      try {
          
          List<Apartment> apartments =  apartmentDao.findAll();
          resp.setResult(apartments);
          resp.setResponseStatus(ResultStatus.RESULT_OK);
      }catch(Exception e) {
          resp.setErrorMsg(e.getMessage());
          resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
      }
      return resp;
    }

    public Response<Room> createRoom(Room room) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public Response<Room> getRoom(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.shinn.service.AdminService#getRooms(java.lang.Integer)
     */
    @Override
    public Response<Room> getRooms(Integer aptId) {
        Response<Room> resp = new Response<Room>();
        try {
            
            List<Room> rooms =  roomDao.getRooms(aptId);
            resp.setResult(rooms);
            resp.setResponseStatus(ResultStatus.RESULT_OK);
        }catch(Exception e) {
            resp.setErrorMsg(e.getMessage());
            resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
        }
        return resp;
    }

    public Response<Expense> createExpenses(Expense expense) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public Response<Expense> getExpense(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
