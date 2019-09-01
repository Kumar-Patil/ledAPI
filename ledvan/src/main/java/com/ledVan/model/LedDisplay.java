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
import java.util.List;
import java.util.Map;
import javax.persistence.Convert;

@Entity
@Table(name = "led_display")
@ApiModel(description = "All details about the LedDisplay. ")
public class LedDisplay implements Serializable {

    @ApiModelProperty(notes = "The database generated LedDisplay ID")
    private long id;

    @ApiModelProperty(notes = "The LedDisplay date")
    private Date date;

    @ApiModelProperty(notes = "The LedDisplay districtName")
    private String vechicleNo;

    @ApiModelProperty(notes = "The LedDisplay vechicleStayArea")
    private String vechicleStayArea;

    @ApiModelProperty(notes = "The LedDisplay reportingTime")
    private String reportingTime;

    @ApiModelProperty(notes = "The LedDisplay closingPlace")
    private String closingPlace;

    @ApiModelProperty(notes = "The LedDisplay closingTime")
    private String closingTime;

    @ApiModelProperty(notes = "The LedDisplay distance")
    private String distance;

    @ApiModelProperty(notes = "The LedDisplay Kilometer Picture")
    private String kilometerPictureName;

    @ApiModelProperty(notes = "The LedDisplay General hour Picture")
    private String generalHourPictureName;

    @ApiModelProperty(notes = "The LedDisplay status")
    private String status;

    @ApiModelProperty(notes = "The LedDisplay createdAt")
    private Date createdAt;

    @ApiModelProperty(notes = "The LedDisplay updatedAt")
    private Date updatedAt;

    @ApiModelProperty(notes = "The LedDisplay json data")
    private List<Display> display;

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

    @Column(name = "status", nullable = true)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "vechicle_stay_area", nullable = true)
    public String getVechicleStayArea() {
        return vechicleStayArea;
    }

    public void setVechicleStayArea(String vechicleStayArea) {
        this.vechicleStayArea = vechicleStayArea;
    }

    @Column(name = "reporting_time", nullable = true)
    public String getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(String reportingTime) {
        this.reportingTime = reportingTime;
    }

    @Column(name = "closing_place", nullable = true)
    public String getClosingPlace() {
        return closingPlace;
    }

    public void setClosingPlace(String closingPlace) {
        this.closingPlace = closingPlace;
    }

    @Column(name = "closing_time", nullable = true)
    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    @Column(name = "distance", nullable = true)
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Column(name = "kilometer_picture_name", nullable = true)
    public String getKilometerPictureName() {
        return kilometerPictureName;
    }

    public void setKilometerPictureName(String kilometerPictureName) {
        this.kilometerPictureName = kilometerPictureName;
    }

    @Column(name = "general_hour_pictureName", nullable = true)
    public String getGeneralHourPictureName() {
        return generalHourPictureName;
    }

    public void setGeneralHourPictureName(String generalHourPictureName) {
        this.generalHourPictureName = generalHourPictureName;
    }

    @Column(columnDefinition = "json")
    @Convert(converter = ListConverter.class)
    public List<Display> getDisplay() {
        return display;
    }

    public void setDisplay(List<Display> display) {
        this.display = display;
    }
    

}
