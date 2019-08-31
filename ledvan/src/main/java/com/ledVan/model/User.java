package com.ledvan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "admin_user")
@ApiModel(description = "All details about the Employee. ")
public class User {

    @ApiModelProperty(notes = "The database generated employee ID")
    private long id;

    @ApiModelProperty(notes = "The employee first name")
    private String firstName;

    @ApiModelProperty(notes = "The employee last name")
    private String lastName;

    @ApiModelProperty(notes = "The employee email id")
    private String emailId;

    @ApiModelProperty(notes = "The employee first name")
    private String userName;

    @ApiModelProperty(notes = "The employee first name")
    private String userPassword;

    @ApiModelProperty(notes = "The employee last name")
    private String mobileNo;

    public User() {

    }

    public User(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
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

    @Column(name = "email_address", nullable = true)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name = "user_name", nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_password", nullable = false)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Column(name = "mobile_no", nullable = true)
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", userName=" + userName + ", userPassword=" + userPassword + '}';
    }

}
