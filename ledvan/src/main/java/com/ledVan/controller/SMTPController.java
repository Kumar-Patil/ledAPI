package com.ledVan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.ledVan.exception.ResourceNotFoundException;
import com.ledVan.model.SMTPDetails;
import com.ledVan.repository.SMTPRepository;
import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "SMTP API List", description = "SMTP API List")
public class SMTPController {

    @Autowired
    private SMTPRepository sMTPRepository;

    /**
     *
     * @return
     */
    @ApiOperation(value = "View a list of available SMTP", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/smtp")
    public List<SMTPDetails> getAll() {
        return (List<SMTPDetails>) sMTPRepository.findAll();
    }

    @ApiOperation(value = "Get smtp by Id")
    @GetMapping("/smtp/{id}")
    public ResponseEntity<SMTPDetails> getById(
            @ApiParam(value = "smtp id from which smtp object will retrieve", required = true)
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        SMTPDetails mTPDetails = sMTPRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("district not found for this id :: " + id));
        return ResponseEntity.ok().body(mTPDetails);
    }

    @ApiOperation(value = "Add district")
    @PostMapping("/smtp")
    public SMTPDetails create(
            @ApiParam(value = "smtp object store in database table", required = true)
            @Valid @RequestBody SMTPDetails mTPDetails) {
        mTPDetails.setCreatedAt(new Date());
        mTPDetails.setUpdatedAt(new Date());
        return sMTPRepository.save(mTPDetails);
    }

    @ApiOperation(value = "Update smtp")
    @PutMapping("/smtp/{id}")
    public ResponseEntity<SMTPDetails> update(
            @ApiParam(value = "smtp Id to update smtp object", required = true)
            @PathVariable(value = "id") Long id,
            @ApiParam(value = "Update district object", required = true)
            @Valid @RequestBody SMTPDetails mTPDetails) throws ResourceNotFoundException {
        SMTPDetails details = sMTPRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SMTPDetails not found for this id :: " + id));

        details.setUpdatedAt(new Date());
        details.setEmailDefaultBody(mTPDetails.getEmailDefaultBody());
        details.setEmailDefaultSubject(mTPDetails.getEmailDefaultSubject());
        details.setEmailPassword(mTPDetails.getEmailPassword());

        details.setFromEmail(mTPDetails.getFromEmail());
        details.setFromName(mTPDetails.getFromName());

        details.setHostName(mTPDetails.getHostName());
        details.setHostPort(mTPDetails.getHostPort());
        details.setToEmail(mTPDetails.getToEmail());
        details.setUseSSL(mTPDetails.isUseSSL());

        final SMTPDetails updatedSMTPDetails = sMTPRepository.save(details);
        return ResponseEntity.ok(updatedSMTPDetails);
    }

    @ApiOperation(value = "Delete smtp")
    @DeleteMapping("/smtp/{id}")
    public Map<String, Boolean> delete(
            @ApiParam(value = "District Id from which district object will delete from database table", required = true)
            @PathVariable(value = "id") Long districtId)
            throws ResourceNotFoundException {
        SMTPDetails district = sMTPRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District not found for this id :: " + districtId));
        sMTPRepository.delete(district);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
