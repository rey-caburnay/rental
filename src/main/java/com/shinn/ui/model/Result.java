package com.shinn.ui.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Result implements Serializable {
    
    private String status;
}
