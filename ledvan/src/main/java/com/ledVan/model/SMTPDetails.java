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
@Table(name = "smpt_details")
@ApiModel(description = "All details about the SMTPDetails. ")
public class SMTPDetails implements Serializable {

    @ApiModelProperty(notes = "The database generated district ID")
    private long id;

    @ApiModelProperty(notes = "The SMTPDetails hostName")
    private String hostName;

    @ApiModelProperty(notes = "The SMTPDetails hostPort")
    private long hostPort;

    @ApiModelProperty(notes = "The SMTPDetails useSSL")
    private boolean useSSL;

    @ApiModelProperty(notes = "The SMTPDetails fromEmail")
    private String fromEmail;

    @ApiModelProperty(notes = "The SMTPDetails fromName")
    private String fromName;

    @ApiModelProperty(notes = "The SMTPDetails emailPassword")
    private String emailPassword;

    @ApiModelProperty(notes = "The SMTPDetails toEmail")
    private String toEmail;

    @ApiModelProperty(notes = "The SMTPDetails emailDefaultSubject")
    private String emailDefaultSubject;

    @ApiModelProperty(notes = "The SMTPDetails emailDefaultBody")
    private String emailDefaultBody;

    @ApiModelProperty(notes = "The SMTPDetails createdAt")
    private Date createdAt;

    @ApiModelProperty(notes = "The SMTPDetails updatedAt")
    private Date updatedAt;

    public SMTPDetails() {

    }

    public SMTPDetails(String hostName, long hostPort, boolean useSSL, String fromEmail, String fromName, String emailPassword, String toEmail, String emailDefaultSubject, String emailDefaultBody, Date createdAt, Date updatedAt) {
        this.hostName = hostName;
        this.hostPort = hostPort;
        this.useSSL = useSSL;
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.emailPassword = emailPassword;
        this.toEmail = toEmail;
        this.emailDefaultSubject = emailDefaultSubject;
        this.emailDefaultBody = emailDefaultBody;
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

    @Column(name = "host_name", nullable = false)
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Column(name = "host_port", nullable = false)
    public long getHostPort() {
        return hostPort;
    }

    public void setHostPort(long hostPort) {
        this.hostPort = hostPort;
    }

    @Column(name = "use_ssl", nullable = false)
    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    @Column(name = "from_email", nullable = true)
    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    @Column(name = "from_name", nullable = true)
    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    @Column(name = "email_password", nullable = false)
    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    @Column(name = "to_email", nullable = false)
    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    @Column(name = "defualt_email_subject", nullable = true)
    public String getEmailDefaultSubject() {
        return emailDefaultSubject;
    }

    public void setEmailDefaultSubject(String emailDefaultSubject) {
        this.emailDefaultSubject = emailDefaultSubject;
    }

    @Column(name = "default_email_body", nullable = true)
    public String getEmailDefaultBody() {
        return emailDefaultBody;
    }

    public void setEmailDefaultBody(String emailDefaultBody) {
        this.emailDefaultBody = emailDefaultBody;
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
        return "SMTPDetails{" + "id=" + id + ", hostName=" + hostName + ", hostPort=" + hostPort + ", useSSL=" + useSSL + ", fromEmail=" + fromEmail + ", fromName=" + fromName + ", emailPassword=" + emailPassword + ", toEmail=" + toEmail + ", emailDefaultSubject=" + emailDefaultSubject + ", emailDefaultBody=" + emailDefaultBody + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
