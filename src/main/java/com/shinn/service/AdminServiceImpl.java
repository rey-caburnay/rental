package com.shinn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinn.dao.repos.ApartmentDao;
import com.shinn.model.Apartment;
import com.shinn.model.Expense;
import com.shinn.model.Response;
import com.shinn.model.Room;

/**
 * administrative service for the system
 * @author shinn
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ApartmentDao apartmentDao;

    public Response<Apartment> createApartment(Apartment apt) throws Exception {

        apartmentDao.saveUpdate(apt);("save-apartment",
                apt.getName(),
                apt.getPersonInCharge(),
                apt.getAptType(),
                apt.getMobileNo(),
                apt.getTelNo(),
                apt.getAddress1(),
                apt.getAddress2(),
                apt.getStatus());
        
        
    }

    public Apartment getApartment(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public void createRoom(Room room) throws Exception {
        // TODO Auto-generated method stub
        
    }

    public Room getRoom(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public void createExpenses(Expense expense) throws Exception {
        // TODO Auto-generated method stub
        
    }

    public Expense getExpense(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
