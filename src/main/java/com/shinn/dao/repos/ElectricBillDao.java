package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.ElectricBill;

public interface ElectricBillDao extends GenericDao<ElectricBill> {

  public List<ElectricBill> getElectricBillByApt(Integer aptId);
  
  public ElectricBill getByAptRoom(Integer aptId, Integer roomId);
  
  public List<ElectricBill> getElectricReport(Integer aptId);
  
}
