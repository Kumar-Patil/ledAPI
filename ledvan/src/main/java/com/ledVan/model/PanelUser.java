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
@Table(name = "panel_user")
@ApiModel(description = "All details about the PanelUser. ")
public class PanelUser implements Serializable {

    @ApiModelProperty(notes = "The database generated PanelUser ID")
    private long id;

    @ApiModelProperty(notes = "The PanelUser districtName")
    private String districtName;

    @ApiModelProperty(notes = "The PanelUser districtId")
    private long districtId;

    @ApiModelProperty(notes = "The PanelUser email")
    private String email;

    @ApiModelProperty(notes = "The PanelUser password")
    private String password;

    @ApiModelProperty(notes = "The PanelUser mobileNumber")
    private String mobileNumber;

    @ApiModelProperty(notes = "The PanelUser createdAt")
    private Date createdAt;

    @ApiModelProperty(notes = "The PanelUser updatedAt")
    private Date updatedAt;

    public PanelUser() {

    }

    public PanelUser(String districtName, long districtId, String email, String password, String mobileNumber, Date createdAt, Date updatedAt) {
        this.districtName = districtName;
        this.districtId = districtId;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
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

    @Column(name = "district_id", nullable = false)
    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "mobile_number", nullable = false)
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "PannelUser{" + "id=" + id + ", districtName=" + districtName + ", districtId=" + districtId + ", email=" + email + ", password=" + password + ", mobileNumber=" + mobileNumber + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
