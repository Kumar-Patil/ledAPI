package com.ledVan.controller;

import com.ledVan.RequestMapper.fileUpload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "Common API List", description = "Common API List")
public class CommonController {

    private static final String APPLICATION_OCTET_STREAM = "application/octet-stream ";

    @ApiOperation(value = "Upload files", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
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

    @ApiOperation(value = "Download files", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = APPLICATION_OCTET_STREAM)
    public @ResponseBody
    HttpEntity<byte[]> downloadB(@RequestParam("name") String name) throws IOException, FileNotFoundException {
        File file = getFile(name);
        byte[] document = FileCopyUtils.copyToByteArray(file);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", APPLICATION_OCTET_STREAM));
        header.set("Content-Disposition", "inline; filename=" + file.getName());
        header.setContentLength(document.length);
        return new HttpEntity<>(document, header);
    }

    private File getFile(String name) throws FileNotFoundException {
        String rootPath = System.getProperty("catalina.home");
        File file = new File(rootPath + File.separator + "images/" + name);
        if (!file.exists()) {
            throw new FileNotFoundException("file with path: " + file.getName() + " was not found.");
        }
        return file;
    }
}
