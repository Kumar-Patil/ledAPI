/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledVan.Util;

import com.ledVan.model.SMTPDetails;

/**
 *
 * @author santopat
 */
public class ReportAsync implements Runnable {

    private final SMTPDetails mTPDetails;
    private final String reportFilePath;

    @Override
    public void run() {
        System.out.println("Running in async--------------");
        MailHelper.sendMail(mTPDetails, reportFilePath);
    }

    public ReportAsync(SMTPDetails mTPDetails, String reportFilePath) {
        this.mTPDetails = mTPDetails;
        this.reportFilePath = reportFilePath;
    }

}
