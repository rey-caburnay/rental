package com.shinn.ui.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * cash
 * @author rc185213
 *
 */
@Getter
@Setter
@ToString
public class Cash implements Serializable {
    
    private String cashReceived;
    private String amountPaid;
    private String cashChange;
    private String balance;
    private String deposit;
}
