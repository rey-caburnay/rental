package com.shinn.service;

import com.shinn.model.Apartment;
import com.shinn.model.Expense;
import com.shinn.model.Response;
import com.shinn.model.Room;

public interface AdminService {
    
    public Response<Apartment> createApartment(Apartment apt) throws Exception;
    public Response<Apartment> getApartment(Integer id) throws Exception;
    
    public Response<Room> createRoom(Room room) throws Exception;
    public Response<Room> getRoom(Integer id) throws Exception;
    
    public Response<Expense> createExpenses(Expense expense) throws Exception;
    public Response<Expense> getExpense(Integer id) throws Exception;

}
