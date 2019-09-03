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
@Table(name = "user_details")
@ApiModel(description = "All details about the Admin. ")
public class Admin implements Serializable {

    @ApiModelProperty(notes = "The database generated district ID")
    private long id;

    @ApiModelProperty(notes = "The Admin firstName")
    private String firstName;

    @ApiModelProperty(notes = "The Admin lastName")
    private String lastName;

    @ApiModelProperty(notes = "The Admin userName")
    private String userName;

    @ApiModelProperty(notes = "The Admin password")
    private String password;

    @ApiModelProperty(notes = "The Admin email")
    private String email;

    @ApiModelProperty(notes = "The Admin mobileNo")
    private String mobileNo;

    @ApiModelProperty(notes = "The Admin createdAt")
    private Date createdAt;

    @ApiModelProperty(notes = "The Admin updatedAt")
    private Date updatedAt;

    public Admin() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = true)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = true)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "user_name", nullable = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password", nullable = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "mobile_no", nullable = true)
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
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
        return "Admin{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password=" + password + ", email=" + email + ", mobileNo=" + mobileNo + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    

}
