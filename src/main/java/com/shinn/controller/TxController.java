package com.shinn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.dao.factory.ResultStatus;
import com.shinn.model.Response;
import com.shinn.model.Transaction;
import com.shinn.model.User;
import com.shinn.service.TransactionService;

@RestController
@RequestMapping(value="/tx")
public class TxController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TxController.class);
    
    @Autowired
    TransactionService transactionService;
    
    @RequestMapping(value = "/savetx", method = RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Transaction>> createTx(@ModelAttribute Transaction tx) {
        logger.debug(tx.toString());
        Response<Transaction> resp = transactionService.createTx(tx);
        logger.debug(resp.toString());
        if(resp.getResponseStatus().equals(ResultStatus.RESULT_OK)){
            return new ResponseEntity<Response<Transaction>>(resp, HttpStatus.OK);
        } 
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
