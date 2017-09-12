package com.shinn.service;

import java.util.List;

import com.shinn.service.model.Apartment;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.ElectricProvider;
import com.shinn.service.model.Expense;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.ui.model.Response;
import com.shinn.service.model.Room;
import com.shinn.service.model.Tenant;
import com.shinn.service.model.User;

/**
 * AdminService is for Retrieval of data and other maintenance creation
 * 
 * @author rbkshinn
 *
 */
public interface AdminService {
  
  /**
   * 
   * @param user
   * @return
   * @throws Exception
   */
  public Response<User> login(String username, String password);
  /**
   * 
   * @param apt
   * @return
   * @throws Exception
   */
  public Response<Apartment> createApartment(Apartment apt) throws Exception;
  /**
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public Response<Apartment> getApartment(Integer id) throws Exception;
  /**
   * 
   * @return
   */
  public Response<Apartment> getApartments();
  /**
   * 
   * @return
   */
  public Response<Apartment> getVacantApartment();
  /**
   * 
   * @param room
   * @return
   * @throws Exception
   */
  public Response<Room> createRoom(Room room) throws Exception;
  /**
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public Response<Room> getRoom(Integer id) throws Exception;
  /**
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public Response<Room> getVacantRoom(Integer id) throws Exception;
  /**
   * 
   * @param aptId
   * @return
   */
  public Response<Room> getRooms(Integer aptId);
  /**
   * 
   * @param expense
   * @return
   * @throws Exception
   */
  public Response<Expense> createExpenses(Expense expense) throws Exception;
  /**
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public Response<Expense> getExpense(Integer id) throws Exception;
  /**
   * 
   * @return
   */
  public Response<Renter> getRenters();
  /**
   * 
   * @param aptId
   * @param roomId
   * @return
   */
  public Response<RenterInfo> getTenant(int aptId, int roomId);

  /**
   * get the electric bills by each property
   * 
   * @param aptId
   * @return
   */
  public Response<ElectricBill> getElectricBills(Integer aptId);
  
  public Response<ElectricProvider> getElectricProvider(String provider);
}
