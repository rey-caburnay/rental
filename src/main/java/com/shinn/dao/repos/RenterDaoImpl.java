package com.shinn.dao.repos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Transaction;
import com.shinn.util.StringUtil;

@Repository
public class RenterDaoImpl extends AbstractDaoImpl<Renter> implements RenterDao {

  public RenterDaoImpl() throws Exception {
    super();
    setClazz(Renter.class);
  }

  @Override
  public Renter getById(Integer id) throws Exception {
    return getObject("get-renter-by-id", id);
  }

  @Override
  public List<Renter> findAll() throws Exception {
    return getListResult("");
  }

  @Override
  public int saveUpdate(Renter model) throws Exception {
    int result = 0;
    if (StringUtil.isNullOrEmpty(model.getId())) {
      result = executeSaveUpate("save-renter", model);
    } else {
      result = executeSaveUpate("update-renter", model);
    }
    return result;
  }

  @Override
  public Renter getRenterByName(String lastname, String firstname, String initial) {
    try {
      return getObject("get-renter-by-name", lastname, firstname, initial);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public List<Renter> getRenters() {
    List<Renter> renters = getListResult("get-active-renters");
    return renters;
  }

  @Override
  public List<Renter> getRentersByApartment(Integer aptId) {
    List<Renter> renters = getListResult("renters-by-apt", aptId);
    return renters;
  }

  @Override
  public Renter getOccupancy(Integer aptId, Integer roomId) {
    return getObject("renter-apt-room", aptId, roomId);  
  }
}
