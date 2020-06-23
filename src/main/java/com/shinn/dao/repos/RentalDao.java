package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Transaction;

public interface RentalDao extends GenericDao<Transaction> {

  /**
   * 
   * @param renterId
   * @return
   */
  public List<Transaction> getTransactionByRenterId(Integer renterId);

  /**
   * 
   * @param renterId
   * @param status
   * @return
   */
  public List<Transaction> getTransactionByRenterId(Integer renterId, String status);

  /**
   * 
   * @param aptId
   * @param roomId
   * @return
   */
  public List<Transaction> getTransactionByApt(Integer aptId, Integer roomId);

  /**
   * 
   * @param aptId
   * @param roomId
   * @param renterId
   * @return
   */
  public Transaction getTransactionByAptRoomRenter(Integer aptId, Integer roomId, Integer renterId);

  public List<Transaction> findByStatus(String status);

  /**
   *
   * @param date format "YYYY-mm-dd"
   * @param status
   * @return
   */
  public List<Transaction> findByDueDateAndStatus(String date, String status);
  
  
}
