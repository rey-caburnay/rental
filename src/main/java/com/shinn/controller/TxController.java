package com.shinn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.dao.factory.ResultStatus;
import com.shinn.service.TransactionService;
import com.shinn.service.model.Collection;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.CollectionForm;
import com.shinn.ui.model.RegistrationForm;
import com.shinn.ui.model.Response;

@RestController
@RequestMapping(value="/tx")
public class TxController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TxController.class);

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/savetx", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<RegistrationForm>> createTx(@RequestBody RegistrationForm tx) {
        logger.debug(tx.toString());
        Response<RegistrationForm> resp = transactionService.createTx(tx);
        logger.debug(resp.toString());
        if(resp.getResponseStatus().equals(ResultStatus.RESULT_OK)){
            return new ResponseEntity<Response<RegistrationForm>>(resp, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/gettx/{renterid}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Transaction>> getTx(@PathVariable String renterid){
        logger.debug(renterid.toString());

        Response<Transaction> resp = transactionService.getTxByRenterId(Integer.parseInt(renterid));
        logger.debug(resp.toString());
        if(resp.getResponseStatus().equals(ResultStatus.RESULT_OK)){
            return new ResponseEntity<Response<Transaction>>(resp, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    /**
     * 
     * @param renterid
     * @return
     */
    @RequestMapping(value = "/getrooms/{renterid}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<RenterInfo>> getRentersInfo(@PathVariable String renterid){
        logger.debug(renterid.toString());

        Response<RenterInfo> resp = transactionService.getRentersInfo(Integer.parseInt(renterid));
        logger.debug(resp.toString());
        if(resp.getResponseStatus().equals(ResultStatus.RESULT_OK)){
            return new ResponseEntity<Response<RenterInfo>>(resp, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    /**
     * 
     * @param renterid
     * @return
     */
    @RequestMapping(value = "/collections", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<CollectionForm>> createCollection(@RequestBody CollectionForm collectionForm) {
        logger.debug(collectionForm.toString());
        Response<CollectionForm> resp = transactionService.createCollection(collectionForm);
        logger.debug(resp.toString());
        if(resp.getResponseStatus().equals(ResultStatus.RESULT_OK)){
            return new ResponseEntity<Response<CollectionForm>>(resp, HttpStatus.OK);
        }
        return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
    }




}
