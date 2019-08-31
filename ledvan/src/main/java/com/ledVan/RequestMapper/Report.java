package com.ledVan.RequestMapper;

import io.swagger.annotations.ApiModel;

/**
 *
 * @author Santosh Patil
 */
@ApiModel(description = "All details about the DownloadReport. ")
public class Report {

    private String reportType;
    private String toAddress;
    private String emailSubject;
    private String emailContentBody;
    private String reportDate;

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    
    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailContentBody() {
        return emailContentBody;
    }

    public void setEmailContentBody(String emailContentBody) {
        this.emailContentBody = emailContentBody;
    }
    
    
}
