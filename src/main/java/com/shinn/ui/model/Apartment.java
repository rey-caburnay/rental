package com.shinn.ui.model;

import java.io.Serializable;
import java.util.List;

import com.shinn.service.model.Room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Apartment implements Serializable {
    
    private Integer id;
    
    private String name;
    
    private String address;
    
    private List<Room> rooms;
    
    private String status;

  
}
