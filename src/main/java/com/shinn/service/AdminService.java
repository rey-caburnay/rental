package com.shinn.service;

import java.util.List;

import com.shinn.service.model.Apartment;
import com.shinn.service.model.Expense;
import com.shinn.service.model.Renter;
import com.shinn.ui.model.Response;
import com.shinn.service.model.Room;

public interface AdminService {
    
    public Response<Apartment> createApartment(Apartment apt) throws Exception;
    public Response<Apartment> getApartment(Integer id) throws Exception;
    public Response<Apartment> getApartments();
    
    public Response<Room> createRoom(Room room) throws Exception;
    public Response<Room> getRoom(Integer id) throws Exception;
    public Response<Room> getRooms(Integer aptId);
    
    public Response<Expense> createExpenses(Expense expense) throws Exception;
    public Response<Expense> getExpense(Integer id) throws Exception;
    
    public Response<Renter> getRenters();

}
