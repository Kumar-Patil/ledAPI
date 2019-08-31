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
@Table(name = "area")
@ApiModel(description = "All details about the Area. ")
public class AreaDetails implements Serializable {

    @ApiModelProperty(notes = "The database generated district ID")
    private long id;

    @ApiModelProperty(notes = "The Area districtName")
    private String districtName;

    @ApiModelProperty(notes = "The Area area name")
    private String areaName;

    @ApiModelProperty(notes = "The Area district name")
    private long districtId;

    @ApiModelProperty(notes = "The district createdAt")
    private Date createdAt;

    @ApiModelProperty(notes = "The district updatedAt")
    private Date updatedAt;

    public AreaDetails() {

    }

    public AreaDetails(String districtName, String areaName, long districtId, Date createdAt, Date updatedAt) {
        this.districtName = districtName;
        this.areaName = areaName;
        this.districtId = districtId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "district_name", nullable = false)
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Column(name = "area_name", nullable = false)
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Column(name = "district_id", nullable = false)
    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
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

    @Override
    public String toString() {
        return "AreaDetails{" + "id=" + id + ", districtName=" + districtName + ", areaName=" + areaName + ", districtId=" + districtId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
