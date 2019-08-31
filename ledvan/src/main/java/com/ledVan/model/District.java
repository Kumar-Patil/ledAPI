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
@Table(name = "district")
@ApiModel(description = "All details about the District. ")
public class District implements Serializable {

    @ApiModelProperty(notes = "The database generated district ID")
    private long id;

    @ApiModelProperty(notes = "The district districtName")
    private String districtName;

    @ApiModelProperty(notes = "The district createdAt")
    private Date createdAt;

    @ApiModelProperty(notes = "The district updatedAt")
    private Date updatedAt;

    public District() {

    }

    public District(String districtName) {
        this.districtName = districtName;
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
        return "District{" + "id=" + id + ", districtName=" + districtName + '}';
    }

}
