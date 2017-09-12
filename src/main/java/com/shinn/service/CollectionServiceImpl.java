package com.shinn.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.CollectionDao;
import com.shinn.dao.repos.ElectricBillDao;
import com.shinn.dao.repos.ElectricCollectionDao;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.ElectricCollection;
import com.shinn.ui.model.ElectricCollectionForm;
import com.shinn.ui.model.Response;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;

@Service
public class CollectionServiceImpl implements CollectionService {

  @Autowired
  CollectionDao collectionDao;
  @Autowired
  ElectricCollectionDao electricCollectionDao;
  @Autowired ElectricBillDao electricBillDao;

  @Override
  public Response<ElectricCollectionForm> saveElectricCollection(ElectricCollectionForm form)
      throws Exception {
    Response<ElectricCollectionForm> resp = new Response<>();

    ElectricCollection electricCollection = new ElectricCollection();
    electricCollection.setAmount(Double.parseDouble(form.getCash().getAmountPaid()));
    electricCollection.setBillingNo(form.getBilling().getBillingNo());
    electricCollection.setCollectionDate(DateUtil.getCurrentDate());
    electricCollection.setDueDate(form.getBilling().getDueDate());
    if (Double.parseDouble(form.getCash().getBalance()) > 0) {
      electricCollection.setOverdue(Double.parseDouble(form.getCash().getBalance()));
    } else {
      electricCollection.setOverdue(Double.parseDouble("-"+form.getCash().getDeposit()));
    }
   
    electricCollection.setStatus(RentStatus.PAID);
    electricCollection.setCashChange(Double.parseDouble(form.getCash().getCashChange()));
    electricCollection.setCashReceived(Double.parseDouble(form.getCash().getCashReceived()));
    int id = electricCollectionDao.saveUpdate(electricCollection);
    electricCollection.setId(id);
    form.setCollection(electricCollection);
    //update electric bill info
//    ElectricBill electricBill = electricBillDao.getByAptRoom(form.getAptId(), form.getRoomId());
//    electricBill.setPayment(Double.parseDouble(form.getCash().getAmountPaid()));
//    electricBill.setPreviousReading(form.getBilling().getCurrentReading());
//    electricBill.setCurrentReading(null);
//    electricBill.setAmount(0d);
//    electricBill.setStatus(RentStatus.PAID);
    
//    electricBill.setDueDate(DateUtil.addDays(new Date(), days)); //due date is for surcharge computation
    resp.setModel(form);
    resp.setResponseStatus(ResultStatus.RESULT_OK);
    electricCollectionDao.commit();

    return resp;
  }

}
