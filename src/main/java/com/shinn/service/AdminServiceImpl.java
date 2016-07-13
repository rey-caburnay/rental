package com.shinn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinn.dao.repos.ApartmentDao;
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

    public Response<Apartment> createApartment(Apartment apt) throws Exception {

        return null;
    }

    public Response<Apartment> getApartment(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public Response<Room> createRoom(Room room) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public Response<Room> getRoom(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
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
