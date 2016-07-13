package com.shinn.ui.model;

public class Room {
    
    private Integer id;
    
    private Integer aptId;
    
    private String name;
    
    private Integer size;
    
    private String status;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the aptId
     */
    public Integer getAptId() {
        return aptId;
    }

    /**
     * @param aptId the aptId to set
     */
    public void setAptId(Integer aptId) {
        this.aptId = aptId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Room [id=" + id + ", aptId=" + aptId + ", name=" + name + ", size=" + size + ", status=" + status + "]";
    }
    
    

}
