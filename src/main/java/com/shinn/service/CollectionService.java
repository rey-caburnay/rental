package com.shinn.service;

import com.shinn.ui.model.ElectricCollectionForm;
import com.shinn.ui.model.Response;

public interface CollectionService {

  Response<ElectricCollectionForm> saveElectricCollection(ElectricCollectionForm electricCollectionForm) throws Exception;
  
  
}
