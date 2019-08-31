package com.ledVan.RequestMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

/**
 *
 * @author Santosh Patil
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "All details about the DownloadReport. ")
public class Login {

    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_passowrd")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" + "userName=" + userName + ", password=" + password + '}';
    }

}
