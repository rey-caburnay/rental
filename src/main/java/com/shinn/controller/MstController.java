package com.shinn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinn.dao.factory.ResultStatus;
import com.shinn.service.AdminService;
import com.shinn.service.model.Apartment;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Room;
import com.shinn.ui.model.RegistrationForm;
import com.shinn.ui.model.Response;
import com.shinn.util.StringUtil;


@RestController
@RequestMapping(value="/mst")
public class MstController {
    private static final Logger logger = LoggerFactory.getLogger(MstController.class);

    @Autowired
    AdminService adminService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/apartments", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Apartment>> getApartments() {

        Response<Apartment>resp = adminService.getApartments();
        logger.debug(resp.toString());
        if(resp.getResponseStatus().equals(ResultStatus.RESULT_OK)){
            return new ResponseEntity<Response<Apartment>>(resp, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/rooms/{aptid}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<RenterInfo>> getRooms(@PathVariable String aptid) {
        logger.info(aptid);
        logger.info(adminService.toString());
        Response<RenterInfo>resp = adminService.getRenters();
        logger.debug(resp.toString());
        if(resp.getResponseStatus().equals(ResultStatus.RESULT_OK)){
            return new ResponseEntity<Response<RenterInfo>>(resp, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/renters", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<RenterInfo>> getRenters() {
        Response<RenterInfo>resp = adminService.getRenters();
        logger.debug(resp.toString());
        if(resp.getResponseStatus().equals(ResultStatus.RESULT_OK)){
            return new ResponseEntity<Response<RenterInfo>>(resp, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
