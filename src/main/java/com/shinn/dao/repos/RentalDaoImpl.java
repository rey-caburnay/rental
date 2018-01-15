package com.shinn.dao.repos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Transaction;


@Repository
public class RentalDaoImpl extends AbstractDaoImpl<Transaction> implements RentalDao {

  public RentalDaoImpl() throws Exception {
    super();
    setClazz(Transaction.class);
  }

  public Transaction getById(Integer id) throws Exception {
    return getObject("get-rental", id);
  }

  public List<Transaction> findAll() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  public int saveUpdate(Transaction model) throws Exception {
    String sqlStment = "save-transaction";
    if (model.getId() != null && model.getId() > 0) {
      sqlStment = "update-transaction";
    }
    return executeSaveUpate(sqlStment, model);

  }

  /**
   * get transaction /membership by renter's id
   */
  @Override
  public List<Transaction> getTransactionByRenterId(Integer renterId) {
    return getListResult("get-transaction-by-renterid", renterId);
  }

  @Override
  public List<Transaction> getTransactionByRenterId(Integer renterId, String status) {
    return getListResult("get-transaction-by-renterid-status", renterId, status);
  }

  @Override
  public List<Transaction> getTransactionByApt(Integer aptId, Integer roomId) {
    List<Transaction> list = new ArrayList<>();
    if (aptId == null) {
      list = getListResult("rental-all");
    } else if (aptId != null && roomId == null) {
      list = getListResult("rental-by-apt", aptId);
    } else if (aptId != null && roomId != null) {
      list =  getListResult("get-transaction-by-apt-room", aptId, roomId);
    }
    return list;
  }


  @Override
  public Transaction getTransactionByAptRoomRenter(Integer aptId, Integer roomId,
      Integer renterId) {
    return getObject("get-transaction-by-apt-room-renter", aptId, roomId, renterId);
  }

  /* (non-Javadoc)
   * @see com.shinn.dao.repos.RentalDao#getActiveTransactions()
   */
  @Override
  public List<Transaction> findByStatus(String status) {
    return getListResult("transaction-findby-status", status);
  }
  
  

}
