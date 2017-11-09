package com.shinn.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.CollectionDao;
import com.shinn.dao.repos.ElectricBillDao;
import com.shinn.dao.repos.RenterDao;
import com.shinn.dao.repos.RoomDao;
import com.shinn.service.model.Apartment;
import com.shinn.service.model.Collection;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.Renter;
import com.shinn.service.model.Room;
import com.shinn.ui.model.Response;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;
import com.shinn.util.StringUtil;

@Service
public class ReportServiceImpl implements ReportService {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

  @Autowired
  RenterDao renterDao;
  @Autowired
  CollectionDao collectionDao;
  @Autowired
  RoomDao roomDao;
  @Autowired
  ElectricBillDao electricBillDao;
  
  @Override
  public Response<Renter> getRentersReport(Integer aptId) {
    Response<Renter> resp = new Response<Renter>();
    try {
      List<Renter> renters = new ArrayList<>();
      if (StringUtil.isNullOrEmpty(aptId)) {
        renters = renterDao.getRenters();
      } else  {
        renters = renterDao.getRentersByApartment(aptId);
      }
      
      for (Renter renter : renters) {
        Collection collection = collectionDao.getLastPayment(renter.getAptId(), renter.getRoomId());
        if(collection != null) {
          renter.setLastPayment(collection.getAmountPaid());
          renter.setLastPaymentDate(collection.getTxDate());
        }
      }
      resp.setResult(renters);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
  }
  
  @Override
  public Response<Room> getRoomsReport(Integer aptId) {
    Response<Room> resp = new Response<Room>();
    try {
      List<Room> rooms = new ArrayList<>();
      if (StringUtil.isNullOrEmpty(aptId)) {
        rooms = roomDao.findAll();
      } else  {
        rooms = roomDao.getRooms(aptId);
      }
      for(Room room : rooms) {
        Renter renter = renterDao.getOccupancy(room.getAptId(), room.getId());
        Collection collection = collectionDao.getLastPayment(room.getAptId(), room.getId());
        String status = RentStatus.OCCUPIED;
        if(renter == null) {
          status = RentStatus.VACANT;
        }
        if(collection!=null) {
          room.setLastPayment(collection.getTxDate());
          room.setUnpaidMonths(DateUtil.getNumberOfMonths(collection.getTxDate(), DateUtil.getCurrentDate()));
        } else {
          room.setLastPayment(null);
          room.setUnpaidMonths(0);
        }
      }
      resp.setResult(rooms);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
  }

  @Override
  public Response<ElectricBill> getElectricReport(Integer aptId) {
    Response<ElectricBill> resp = new Response<ElectricBill>();
    try {
      List<ElectricBill> bills = new ArrayList<>();
      bills = electricBillDao.getElectricReport(aptId);
      resp.setResult(bills);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
  }

}
