package com.ledVan.controller;

import com.ledVan.RequestMapper.Report;
import com.ledVan.RequestMapper.fileUpload;
import com.ledVan.Util.MailHelper;
import com.ledVan.exception.ResourceNotFoundException;
import com.ledVan.model.SMTPDetails;
import com.ledVan.repository.SMTPRepository;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "Report API List", description = "Report API List")
public class ReportController {

    private static final String EXTERNAL_FILE_PATH = "C:\\Patil\\AWARD\\";

    @Autowired
    private SMTPRepository sMTPRepository;

    /**
     *
     * @param reportType
     * @param date
     * @param area
     * @param request
     * @param response
     * @param fileName
     * @return
     * @throws java.io.IOException
     */
    @ApiOperation(value = "Get downloadDailyReport", response = Map.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/download/report/daily")
    public void downloadDailyReport(@RequestParam String reportType, @RequestParam String date, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = "report.pdf";
        File file = new File(EXTERNAL_FILE_PATH + fileName);
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
            //Here we have mentioned it to show as attachment
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }

    @ApiOperation(value = "Send report")
    @PostMapping("/send/report")
    public Map<String, Boolean> send(
            @ApiParam(value = "area object store in database table", required = true)
            @Valid @RequestBody Report report) throws ResourceNotFoundException {
        //Util.sendmail();
        long id = 1;
        SMTPDetails mTPDetails = sMTPRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SMTP not found for this id :: " + id));
        MailHelper.sendMail(mTPDetails, "C:\\Patil\\AWARD\\report.pdf");
        Map<String, Boolean> response = new HashMap<>();
        response.put("sent", Boolean.TRUE);
        return response;
    }

    /**
     *
     * @param reportType
     * @param date
     * @param area
     * @param request
     * @param response
     * @param fileName
     * @return
     * @throws java.io.IOException
     */
    @ApiOperation(value = "Get downloadMonthlyReport", response = Map.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/download/report/monthly")
    public void downloadMonthlyReport(@RequestParam String district, @RequestParam String date, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = "report.pdf";
        File file = new File(EXTERNAL_FILE_PATH + fileName);
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
            //Here we have mentioned it to show as attachment
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFileHandler(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "images");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String fileName = System.currentTimeMillis() + name;
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + fileName);
                try ( BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile))) {
                    stream.write(bytes);
                }
                return new ResponseEntity<>(new fileUpload(fileName), HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>("You failed to upload " + name + " => " + e.getMessage(), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("You failed to upload " + name
                    + " because the file was empty.", HttpStatus.OK);
        }
    }

}
