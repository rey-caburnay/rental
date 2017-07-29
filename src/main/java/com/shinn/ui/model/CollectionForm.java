package com.shinn.ui.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.shinn.service.model.CollectionDetails;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Transaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * model to be shared by ui
 * @author rc185213
 *
 */
@lombok.ToString
public class CollectionForm implements Serializable {
    
    private int aptId;
    private int roomId;
    private int renterId;
    private String paymentType;
    private String userId;
    private Date txDate;
    private Date startDate;
    private Date dueDate;
    private List<TransactionDetails> transactions;
    private List<RenterInfo> tenants;
    private Cash cash;
    private Credit credit;
    private Paypal paypal;
    private String note;
    private String recievedBy;
    
    /**
	 * @return the renterId
	 */
	public int getRenterId() {
		return renterId;
	}
	/**
	 * @param renterId the renterId to set
	 */
	public void setRenterId(int renterId) {
		this.renterId = renterId;
	}
	/**
	 * @return the paypal
	 */
	public Paypal getPaypal() {
		return paypal;
	}
	/**
	 * @param paypal the paypal to set
	 */
	public void setPaypal(Paypal paypal) {
		this.paypal = paypal;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the recievedBy
	 */
	public String getRecievedBy() {
		return recievedBy;
	}
	/**
	 * @param recievedBy the recievedBy to set
	 */
	public void setRecievedBy(String recievedBy) {
		this.recievedBy = recievedBy;
	}
	/**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }
    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return the transactions
     */
    public List<TransactionDetails> getTransactions() {
        return transactions;
    }
    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(List<TransactionDetails> transactions) {
        this.transactions = transactions;
    }
    /**
     * @return the cash
     */
    public Cash getCash() {
        return cash;
    }
    /**
     * @param cash the cash to set
     */
    public void setCash(Cash cash) {
        this.cash = cash;
    }
    /**
     * @return the credit
     */
    public Credit getCredit() {
        return credit;
    }
    /**
     * @param credit the credit to set
     */
    public void setCredit(Credit credit) {
        this.credit = credit;
    }
    /**
     * @return the tenants
     */
    public List<RenterInfo> getTenants() {
      return tenants;
    }
    /**
     * @param tenants the tenants to set
     */
    public void setTenants(List<RenterInfo> tenants) {
      this.tenants = tenants;
    }
    /**
     * @return the aptId
     */
    public int getAptId() {
      return aptId;
    }
    /**
     * @param aptId the aptId to set
     */
    public void setAptId(int aptId) {
      this.aptId = aptId;
    }
    /**
     * @return the roomId
     */
    public int getRoomId() {
      return roomId;
    }
    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(int roomId) {
      this.roomId = roomId;
    }
    /**
     * @return the txDate
     */
    public Date getTxDate() {
      return txDate;
    }
    /**
     * @param txDate the txDate to set
     */
    public void setTxDate(Date txDate) {
      this.txDate = txDate;
    }
    /**
     * @return the startDate
     */
    public Date getStartDate() {
      return startDate;
    }
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
      this.startDate = startDate;
    }
    /**
     * @return the dueDate
     */
    public Date getDueDate() {
      return dueDate;
    }
    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
      this.dueDate = dueDate;
    }
    
}
