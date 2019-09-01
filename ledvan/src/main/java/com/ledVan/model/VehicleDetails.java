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
@Table(name = "vehicle_details")
@ApiModel(description = "All details about the VehicleDetails. ")
public class VehicleDetails implements Serializable {

    @ApiModelProperty(notes = "The database generated VehicleDetails ID")
    private long id;

    @ApiModelProperty(notes = "The VehicleDetails vehicle no")
    private String vehicleNo;

    @ApiModelProperty(notes = "The VehicleDetails driver name")
    private String driverName;

    @ApiModelProperty(notes = "The VehicleDetails driverMobileNo")
    private String driverMobileNo;

    @ApiModelProperty(notes = "The VehicleDetails driverPicture")
    private String driverPicture;

    @ApiModelProperty(notes = "The VehicleDetails operatorPicture")
    private String operatorPicture;

    @ApiModelProperty(notes = "The VehicleDetails districtAreaName")
    private String districtAreaName;

    @ApiModelProperty(notes = "The VehicleDetails districtAreaName")
    private long districtAreaId;

    @ApiModelProperty(notes = "The VehicleDetails operatorName")
    private String operatorName;

    @ApiModelProperty(notes = "The VehicleDetails operatorMobileNo")
    private String operatorMobileNo;

    @ApiModelProperty(notes = "The VehicleDetails vehicleDetails")
    private String vehicleDetails;

    @ApiModelProperty(notes = "The district createdAt")
    private Date createdAt;

    @ApiModelProperty(notes = "The district updatedAt")
    private Date updatedAt;

    @ApiModelProperty(notes = "The VehicleDetails districtName")
    private String districtName;

    public VehicleDetails() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "vehicle_no", nullable = false)
    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    @Column(name = "driver_name", nullable = false)
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Column(name = "driver_mobile_no", nullable = false)
    public String getDriverMobileNo() {
        return driverMobileNo;
    }

    public void setDriverMobileNo(String driverMobileNo) {
        this.driverMobileNo = driverMobileNo;
    }

    @Column(name = "driver_picture", nullable = true)
    public String getDriverPicture() {
        return driverPicture;
    }

    public void setDriverPicture(String driverPicture) {
        this.driverPicture = driverPicture;
    }

    @Column(name = "operator_picture", nullable = true)
    public String getOperatorPicture() {
        return operatorPicture;
    }

    public void setOperatorPicture(String operatorPicture) {
        this.operatorPicture = operatorPicture;
    }

    @Column(name = "district_area_name", nullable = false)
    public String getDistrictAreaName() {
        return districtAreaName;
    }

    public void setDistrictAreaName(String districtAreaName) {
        this.districtAreaName = districtAreaName;
    }

    @Column(name = "district_area_id", nullable = false)
    public long getDistrictAreaId() {
        return districtAreaId;
    }

    public void setDistrictAreaId(long districtAreaId) {
        this.districtAreaId = districtAreaId;
    }

    @Column(name = "opertaor_name", nullable = true)
    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Column(name = "operator_mobile_no", nullable = true)
    public String getOperatorMobileNo() {
        return operatorMobileNo;
    }

    public void setOperatorMobileNo(String operatorMobileNo) {
        this.operatorMobileNo = operatorMobileNo;
    }

    @Column(name = "vehicle_details", nullable = true)
    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public VehicleDetails(String vehicleNo, String driverName, String driverMobileNo, String driverPicture, String operatorPicture, String districtAreaName, long districtAreaId, String operatorName, String operatorMobileNo, String vehicleDetails) {
        this.vehicleNo = vehicleNo;
        this.driverName = driverName;
        this.driverMobileNo = driverMobileNo;
        this.driverPicture = driverPicture;
        this.operatorPicture = operatorPicture;
        this.districtAreaName = districtAreaName;
        this.districtAreaId = districtAreaId;
        this.operatorName = operatorName;
        this.operatorMobileNo = operatorMobileNo;
        this.vehicleDetails = vehicleDetails;
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

    @Column(name = "district_name", nullable = true)
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return "VehicleDetails{" + "id=" + id + ", vehicleNo=" + vehicleNo + ", driverName=" + driverName + ", driverMobileNo=" + driverMobileNo + ", driverPicture=" + driverPicture + ", operatorPicture=" + operatorPicture + ", districtAreaName=" + districtAreaName + ", districtAreaId=" + districtAreaId + ", operatorName=" + operatorName + ", operatorMobileNo=" + operatorMobileNo + ", vehicleDetails=" + vehicleDetails + '}';
    }

}
