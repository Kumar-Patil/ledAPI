package com.ledVan.RequestMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

/**
 *
 * @author santopat
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "All details about the DownloadReport. ")
public class GenerateReport {

    @JsonProperty("to_address")
    private String toAddress;

    @JsonProperty("email_subject")
    private String emailSubject;

    @JsonProperty("email_content_body")
    private String emailContentBody;

    @JsonProperty("report_date")
    private String reportDate;

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

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public GenerateReport(String toAddress, String emailSubject, String emailContentBody, String reportDate) {
        this.toAddress = toAddress;
        this.emailSubject = emailSubject;
        this.emailContentBody = emailContentBody;
        this.reportDate = reportDate;
    }

}
