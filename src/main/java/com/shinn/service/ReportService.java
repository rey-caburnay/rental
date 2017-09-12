package com.shinn.service;

import java.util.List;

import com.shinn.service.model.Renter;
import com.shinn.service.model.Room;
import com.shinn.ui.model.Response;

public interface ReportService {

  public Response<Renter> getRentersReport(Integer aptId);
  
  public Response<Room> getRoomsReport(Integer aptId);
}
