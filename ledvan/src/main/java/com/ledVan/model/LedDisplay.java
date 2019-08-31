package com.ledVan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "led_display")
@ApiModel(description = "All details about the LedDisplay. ")
public class LedDisplay implements Serializable {

    @ApiModelProperty(notes = "The database generated LedDisplay ID")
    private long id;

    @ApiModelProperty(notes = "The LedDisplay districtName")
    private String vechicleNo;

    @ApiModelProperty(notes = "The LedDisplay status")
    private String status;

    @ApiModelProperty(notes = "The LedDisplay createdAt")
    private Date createdAt;

    @ApiModelProperty(notes = "The LedDisplay updatedAt")
    private Date updatedAt;

    public LedDisplay() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "created_at", nullable = true)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at", nullable = true)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column(name = "vechicle_no", nullable = false)
    public String getVechicleNo() {
        return vechicleNo;
    }

    public void setVechicleNo(String vechicleNo) {
        this.vechicleNo = vechicleNo;
    }

    @Column(name = "status", nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
