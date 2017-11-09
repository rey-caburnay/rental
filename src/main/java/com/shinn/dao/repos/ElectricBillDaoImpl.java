package com.shinn.dao.repos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.ElectricBill;

@Repository
public class ElectricBillDaoImpl extends AbstractDaoImpl<ElectricBill> implements ElectricBillDao {

  public ElectricBillDaoImpl() throws Exception {
    super();
    setClazz(ElectricBill.class);
  }

  @Override
  public ElectricBill getById(Integer id) throws Exception {
    //TODO no sql query created
    return getObject("get-electric-bill", id);
  }

  @Override
  public List<ElectricBill> findAll() throws Exception {
    //TODO no sql query created
    return getListResult("find-all-electric");
  }

  @Override
  public int saveUpdate(ElectricBill model) throws Exception {
    String sqlStment = "save-electricbill";
    if (model.getId() != null && model.getId() > 0) {
        sqlStment = "update-electricbill";
    } 
    return executeSaveUpate(sqlStment, model);
  }

  @Override
  public List<ElectricBill> getElectricBillByApt(Integer aptId) {
    return getListResult("electric-findby-apt", aptId);
  }

  @Override
  public ElectricBill getByAptRoom(Integer aptId, Integer roomId) {
    return getObject("electricbill-findby-apt-room", aptId, roomId);
  }

  @Override
  public List<ElectricBill> getElectricReport(Integer aptId) {
    List<ElectricBill> list = new ArrayList<>();
   System.out.println(aptId);
    if(aptId == null) {
      list = getListResult("electric-report");
    } else {
      list = getListResult("electric-report-by-apt", aptId);
    }
    return list;
  }

}
